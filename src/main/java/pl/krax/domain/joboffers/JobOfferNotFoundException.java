package pl.krax.domain.joboffers;

class JobOfferNotFoundException extends RuntimeException {
    JobOfferNotFoundException(String message) {
        super(message);
    }
}