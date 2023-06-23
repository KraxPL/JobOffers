package pl.krax.domain.joboffers;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class JobOffer {
    private Long id;
    private String title;
    private String content;
    private String salary;
    private String url;
}
