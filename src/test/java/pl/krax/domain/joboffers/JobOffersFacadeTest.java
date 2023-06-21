package pl.krax.domain.joboffers;

import com.github.dockerjava.api.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class JobOffersFacadeTest {

    @Mock
    private JobOffersRepositoryInterface jobOfferRepository;
    @Mock
    private JobOfferServiceInterface jobOfferServiceInterface;

    private JobOffersFacade jobOffersFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jobOffersFacade = new JobOffersFacade(jobOfferRepository, jobOfferServiceInterface);
    }

    @Test
    void should_save_four_offers_when_there_is_no_offer_in_database() {
        // Arrange
        when(jobOfferRepository.getAllOffers()).thenReturn(new ArrayList<>());

        // Act
        jobOffersFacade.saveJobOffersFromRemote();

        // Assert
        verify(jobOfferRepository, times(4)).saveJobOffer(any(JobOffer.class));
    }

    @Test
    void should_save_two_offers_when_repository_had_four_added_with_offer_urls() {
        // Arrange
        List<JobOffer> existingOffers = new ArrayList<>();
        existingOffers.add(JobOffer.builder().id(1L).title("Title 1").content("Content 1").salary("1000").url("URL 1").build());
        existingOffers.add(JobOffer.builder().id(2L).title("Title 2").content("Content 2").salary("2000").url("URL 2").build());
        when(jobOfferRepository.getAllOffers()).thenReturn(existingOffers);

        List<JobOffer> remoteOffers = new ArrayList<>();
        remoteOffers.add(JobOffer.builder().id(3L).title("Title 3").content("Content 3").salary("3000").url("URL 3").build());
        remoteOffers.add(JobOffer.builder().id(4L).title("Title 4").content("Content 4").salary("4000").url("URL 4").build());
        remoteOffers.add(JobOffer.builder().id(5L).title("Title 5").content("Content 5").salary("5000").url("URL 5").build());
        remoteOffers.add(JobOffer.builder().id(6L).title("Title 6").content("Content 6").salary("6000").url("URL 6").build());
        when(jobOfferServiceInterface.fetchJobOffers()).thenReturn(remoteOffers);

        // Act
        jobOffersFacade.saveJobOffersFromRemote();

        // Assert
        verify(jobOfferRepository, times(2)).saveJobOffer(any(JobOffer.class));
    }

    @Test
    void should_throw_duplicate_key_exception_when_with_offer_url_exists() {
        // Arrange
        List<JobOffer> existingOffers = new ArrayList<>();
        existingOffers.add(JobOffer.builder().id(1L).title("Title 1").content("Content 1").salary("1000").url("URL 1").build());
        when(jobOfferRepository.getAllOffers()).thenReturn(existingOffers);

        JobOffer newJobOffer = JobOffer.builder().id(2L).title("Title 2").content("Content 2").salary("2000").url("URL 1").build();

        // Act and Assert
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            jobOffersFacade.saveJobOffer(newJobOffer);
        });
    }

    @Test
    void should_throw_not_found_exception_when_offer_not_found() {
        // Arrange
        long nonExistingOfferId = 123L;
        when(jobOfferRepository.findJobOfferById(nonExistingOfferId)).thenReturn(null);

        // Act and Assert
        Assertions.assertThrows(NotFoundException.class, () -> {
            jobOffersFacade.findJobOfferById(nonExistingOfferId);
        });
    }

    @Test
    void should_fetch_from_jobs_from_remote_and_save_all_offers_when_repository_is_empty() {
        // Arrange
        when(jobOfferRepository.getAllOffers()).thenReturn(new ArrayList<>());

        List<JobOffer> remoteOffers = new ArrayList<>();
        remoteOffers.add(JobOffer.builder().id(1L).title("Title 1").content("Content 1").salary("1000").url("URL 1").build());
        remoteOffers.add(JobOffer.builder().id(2L).title("Title 2").content("Content 2").salary("2000").url("URL 2").build());
        remoteOffers.add(JobOffer.builder().id(3L).title("Title 3").content("Content 3").salary("3000").url("URL 3").build());
        remoteOffers.add(JobOffer.builder().id(4L).title("Title 4").content("Content 4").salary("4000").url("URL 4").build());
        when(jobOfferServiceInterface.fetchJobOffers()).thenReturn(remoteOffers);

        // Act
        jobOffersFacade.saveJobOffersFromRemote();

        // Assert
        verify(jobOfferRepository, times(4)).saveJobOffer(any(JobOffer.class));
    }

    @Test
    void should_find_offer_by_id_when_offer_was_saved() {
        // Arrange
        long existingOfferId = 123L;
        JobOffer existingJobOffer = JobOffer.builder().id(existingOfferId).title("Title").content("Content").salary("1000").url("URL").build();
        when(jobOfferRepository.findJobOfferById(existingOfferId)).thenReturn(existingJobOffer);

        // Act
        JobOffer foundJobOffer = jobOffersFacade.findJobOfferById(existingOfferId);

        // Assert
        Assertions.assertEquals(existingJobOffer, foundJobOffer);
    }
}

