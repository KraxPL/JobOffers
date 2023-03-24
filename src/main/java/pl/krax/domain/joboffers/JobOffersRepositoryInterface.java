package pl.krax.domain.joboffers;

import java.util.Collection;

interface JobOffersRepositoryInterface {
    JobOffer save(JobOffer jobOffer);
    Collection<JobOffer> findAll();
    JobOffer findById(Long id);
}
