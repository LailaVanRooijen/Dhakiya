package com.lvr.Dhakiya_backend.entities.tag;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.enums.Status;
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

  public Status getStatus() {
    double percentage = getPercentage();
    if (this.seenCount == 0) {
      return Status.NO_DATA;
    }

    if (percentage >= 90) {
      return Status.VERY_STRONG;
    } else if (percentage >= 70) {
      return Status.STRONG;
    } else if (percentage >= 50) {
      return Status.GOOD;
    } else {
      return Status.WEAK;
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
