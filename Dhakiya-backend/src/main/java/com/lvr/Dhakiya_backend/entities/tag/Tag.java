package com.lvr.Dhakiya_backend.entities.tag;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Tag {
  @GeneratedValue @Id Long id;
  @Setter private String tag;
  @Setter private Integer seenCount;
  @Setter private Integer flaggedNegativeCount;

  @Setter @ManyToOne private Environment environment;

  public Tag(String tag) {
    this.tag = tag;
    this.seenCount = 0;
    this.flaggedNegativeCount = 0;
  }

  public TagStatus getStatus() {
    double negativePercentage = (double) flaggedNegativeCount / seenCount * 100;

    if (negativePercentage <= 15) {
      return TagStatus.VERY_STRONG;
    } else if (negativePercentage <= 30) {
      return TagStatus.STRONG;
    } else if (negativePercentage <= 60) {
      return TagStatus.GOOD;
    } else {
      return TagStatus.WEAK;
    }
  }
}
