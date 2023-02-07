package org.bolcom.app.infrastracture.entities;

import lombok.*;
import org.bolcom.app.domain.models.Pits;

import javax.persistence.*;

@Entity
@Table(name = "PITS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PitsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    @Column(name = "a_first_pit")
    private int firstPit;
    @Column(name = "b_second_pit")
    private int secondPit;
    @Column(name = "c_third_pit")
    private int thirdPit;
    @Column(name = "d_fourth_pit")
    private int fourthPit;
    @Column(name = "c_fifth_pit")
    private int fifthPit;
    @Column(name = "e_sixth_pit")
    private int sixthPit;
    @Column(name = "f_big_pit")
    private int bigPit;




    public Pits toPits(){
        return new Pits(firstPit,secondPit,thirdPit,fourthPit,fifthPit,sixthPit,bigPit);
    }

    public static PitsEntity from(Pits pits){
        return PitsEntity.builder()
                .firstPit(pits.getLittlePits()[0])
                .secondPit(pits.getLittlePits()[1])
                .thirdPit(pits.getLittlePits()[2])
                .fourthPit(pits.getLittlePits()[3])
                .fifthPit(pits.getLittlePits()[4])
                .sixthPit(pits.getLittlePits()[5])
                .bigPit(pits.getBigPit())
                .build();
    }


}
