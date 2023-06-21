package pl.krax.domain.joboffers;

import java.util.List;

interface JobOffersRepositoryInterface {
    JobOffer findJobOfferById(Long id);
    void saveJobOffer(JobOffer jobOffer);
    List<JobOffer> getAllOffers();
    boolean existsByUrl(String url);
}
