package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.entity.FineEntity;
import com.hacker.boooks.repository.FineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FineServiceImplTest {

    @InjectMocks
    private FineServiceImpl underTest;

    @Mock
    private FineRepository fineRepository;

    @Test
    void getDaysAfterFineIsCharged() {

        // when
        underTest.getDaysAfterFineIsCharged();

        // then
        verify(fineRepository).getDaysAfterFineIsCharged();

    }

    @Test
    void getFineAmount() {

        // when
        underTest.getFineAmount();

        // then
        verify(fineRepository).getFineAmount();

    }

    @Test
    void setDaysForFine() {

        // given
        FineEntity fineEntity = new FineEntity(1, 5, 7.5f);

        when(fineRepository.getById(anyInt())).thenReturn(fineEntity);

        // when
        underTest.setDaysForFine(anyInt());

        // then
        verify(fineRepository).save(any());

    }

    @Test
    void setAmount() {

        // given
        FineEntity fineEntity = new FineEntity(1, 5, 7.5f);

        when(fineRepository.getById(anyInt())).thenReturn(fineEntity);

        // when
        underTest.setAmount(anyFloat());

        // then
        verify(fineRepository).save(any());

    }
}