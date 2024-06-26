package com.example.demo.items;

import com.example.demo.items.model.Item;
import com.example.demo.util.model.exceptions.EntityNotFoundException;
import com.example.demo.util.model.exceptions.UnprocessableContentException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ItemServiceTest {

    @Mock
    private ItemsRepository repository;

    private ItemsService itemService;

    @BeforeEach
    public void setup() {
        itemService = new ItemsService(repository);
    }

    @Test
    public void createItemTest() {
        Item item = new Item();
        item.setName("testItem");
        item.setAmount(3L);
        item.setUnit(Item.Unit.KG);

        Item returned = new Item();
        returned.setId("abcd");
        returned.setName("testItem");
        returned.setAmount(3L);
        returned.setUnit(Item.Unit.KG);

        when(repository.save(item)).thenReturn(returned);

        Item createdItem = itemService.create(item);
        assertNotNull(createdItem.getId(), "Id is null!");
        assertEquals(item.getName(), createdItem.getName());
        assertEquals(item.getUnit(), createdItem.getUnit());
        assertEquals(item.getAmount(), createdItem.getAmount());

        when(repository.findByName(item.getName())).thenReturn(Optional.of(returned));
        assertThrows(
                UnprocessableContentException.class,
                () -> itemService.create(item),
                "Did not throw exception when item exists."
        );
    }

    @Test
    public void updateItemTest() {
        Item item = new Item();
        item.setName("testItem");
        item.setAmount(3L);
        item.setUnit(Item.Unit.KG);
        assertThrows(
                UnprocessableContentException.class,
                () -> itemService.update(item),
                "Did not throw exception when item id is empty."
        );

        item.setId("asdfg");
        when(repository.findById(item.getName())).thenReturn(Optional.empty());
        assertThrows(
                EntityNotFoundException.class,
                () -> itemService.update(item),
                "Did not throw exception when item does not exist."
        );
        verify(repository, times(1)).findById(item.getId());

        Item oldItem = new Item();
        oldItem.setName("testItem2");
        oldItem.setAmount(4L);
        oldItem.setUnit(Item.Unit.PC);
        oldItem.setId("asdfg");
        when(repository.findById(item.getId())).thenReturn(Optional.of(oldItem));

        Item updated = itemService.update(item);
        verify(repository, times(2)).findById(item.getId());
        verify(repository, times(1)).save(item);
    }

}
