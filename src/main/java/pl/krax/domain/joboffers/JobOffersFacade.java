package pl.krax.domain.joboffers;

import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import java.util.List;

@AllArgsConstructor
public class JobOffersFacade {
    private final JobOffersRepositoryInterface jobOfferRepository;
    private final JobOfferServiceInterface jobOfferServiceInterface;

    public void saveJobOffersFromRemote() {
        List<JobOffer> existingOffers = jobOfferRepository.getAllOffers();
        if (existingOffers.isEmpty()) {
            List<JobOffer> remoteOffers = jobOfferServiceInterface.fetchJobOffers();
            remoteOffers.forEach(jobOfferRepository::saveJobOffer);
        }
    }

    public void saveJobOffer(JobOffer jobOffer) {
        if (jobOfferRepository.existsByUrl(jobOffer.getUrl())) {
            throw new DuplicateKeyException("Job offer with the same URL already exists.");
        }
        jobOfferRepository.saveJobOffer(jobOffer);
    }

    public JobOffer findJobOfferById(Long id) {
        JobOffer jobOffer = jobOfferRepository.findJobOfferById(id);
        if (jobOffer == null) {
            throw new JobOfferNotFoundException("Job offer with ID " + id + " not found.");
        }
        return jobOffer;
    }
}
