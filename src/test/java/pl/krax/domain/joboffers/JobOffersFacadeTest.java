package pl.krax.domain.joboffers;

import org.junit.jupiter.api.Test;
import pl.krax.domain.joboffers.dto.JobOfferDto;

import static org.assertj.core.api.Assertions.assertThat;

class JobOffersFacadeTest {
    private final JobOffersRepositoryInterface jobOffersRepository = new JobOfferRepositoryTestImplementation();
    @Test
    public void shouldReturnSuccessWhenSavingJobOffer(){
        JobOffersFacade jobOffersFacade = new JobOffersFacade(jobOffersRepository);

        String result = jobOffersFacade.saveOffer(new JobOfferDto(1L, "Junior Java Developer", "Opis pracy", "4000"));

        assertThat(result).isEqualTo("Success");
    }
//    @Test
//    public void shouldReturnFailWhenJobOfferWasNotSaved() {
//        JobOffersFacade jobOffersFacade = new JobOffersFacade(jobOffersRepository);
//
//        String result = jobOffersFacade.saveOffer(new JobOfferDto(1L, "Junior Java Developer", "Opis pracy", "4000"));
//
//        assertThat(result).isEqualTo("Success");
//    }
}