package pl.krax.domain.joboffers;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JobOfferRepositoryTestImplementation implements JobOffersRepositoryInterface{
    @Getter
    private final Map<String, JobOffer> jobOfferList = new ConcurrentHashMap<>();

    @Override
    public JobOffer findJobOfferById(Long id) {
        return null;
    }

    @Override
    public void saveJobOffer(JobOffer jobOffer) {

    }

    @Override
    public List<JobOffer> getAllOffers() {
        return null;
    }

    @Override
    public boolean existsByUrl(String url) {
        return false;
    }
}
