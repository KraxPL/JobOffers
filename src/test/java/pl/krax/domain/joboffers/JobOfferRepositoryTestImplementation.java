package pl.krax.domain.joboffers;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JobOfferRepositoryTestImplementation implements JobOffersRepositoryInterface{
    private final Map<String, JobOffer> jobOfferList = new ConcurrentHashMap<>();

    @Override
    public JobOffer save(JobOffer jobOffer) {
        jobOfferList.put(String.valueOf(jobOffer.id()), jobOffer);
        return jobOffer;
    }

    @Override
    public Collection<JobOffer> findAll() {
        return (Collection<JobOffer>) jobOfferList;
    }

    @Override
    public JobOffer findById(Long id) {
        return jobOfferList.get(String.valueOf(id));
    }
}
