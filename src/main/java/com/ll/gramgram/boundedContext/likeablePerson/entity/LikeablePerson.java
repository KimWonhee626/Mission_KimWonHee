package com.ll.gramgram.boundedContext.likeablePerson.entity;

import com.ll.gramgram.boundedContext.instaMember.entity.InstaMember;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString
@Entity
@Getter
public class LikeablePerson {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @ManyToOne
    @ToString.Exclude // 양방향을 걸면, 여기에 달아주는게 보통이다. 이렇게 해야 무한재귀가 실행되지 않는다.
    private InstaMember fromInstaMember; // 호감을 표시한 사람(인스타 멤버)

    private String fromInstaMemberUsername; // 혹시 몰라서 기록

    @ManyToOne
    @ToString.Exclude
    private InstaMember toInstaMember; // 호감 표시를 받은사람(인스타 멤버)

    private String toInstaMemberUsername; // 혹시 몰라서 기록

    private int attractiveTypeCode; // 매력포인트(1=외모, 2=성격, 3=능력)

    public String getAttractiveTypeDisplayName() {
        return switch (attractiveTypeCode) {
            case 1 -> "외모";
            case 2 -> "성격";

            default -> "능력";
        };
    }

    public boolean updateAttractiveTypeCode(int attractiveTypeCode){
        if(this.attractiveTypeCode == attractiveTypeCode){
            return false;
        }

        toInstaMember.decreaseLikesCount(fromInstaMember.getGender(), this.attractiveTypeCode);
        toInstaMember.increaseLikesCount(fromInstaMember.getGender(), attractiveTypeCode);

        this.attractiveTypeCode = attractiveTypeCode;
        return true;
    }

    public String getAttractiveTypeDisplayNameWithIcon() {
        return switch (attractiveTypeCode) {
            case 1 -> "<i class=\"fa-solid fa-person-rays\"></i>";
            case 2 -> "<i class=\"fa-regular fa-face-smile\"></i>";
            default -> "<i class=\"fa-solid fa-people-roof\"></i>";
        } + "&nbsp;" + getAttractiveTypeDisplayName();
    }
}
