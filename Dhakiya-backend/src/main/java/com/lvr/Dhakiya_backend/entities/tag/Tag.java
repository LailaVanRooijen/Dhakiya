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
  @Setter private Integer flaggedPositiveCount;

  @Setter @ManyToOne private Environment environment;

  public Tag(String tag) {
    this.tag = tag;
    this.seenCount = 0;
    this.flaggedPositiveCount = 0;
  }

  public TagStatus getStatus() {
    double percentage = getPercentage();
    if (this.seenCount == 0) {
      return TagStatus.NO_DATA;
    }

    if (percentage >= 90) {
      return TagStatus.VERY_STRONG;
    } else if (percentage >= 70) {
      return TagStatus.STRONG;
    } else if (percentage >= 50) {
      return TagStatus.GOOD;
    } else {
      return TagStatus.WEAK;
    }
  }

  public double getPercentage() {
    if (this.flaggedPositiveCount == 0 || this.seenCount == 0) {
      return 0;
    }

    double percentage = (double) flaggedPositiveCount / seenCount * 100;
    return Math.round(percentage);
  }
}
