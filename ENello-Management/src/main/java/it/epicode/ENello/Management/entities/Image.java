package it.epicode.ENello.Management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Image extends BaseEntity{

        private String name;
        private String url;

        @OneToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;
    }

