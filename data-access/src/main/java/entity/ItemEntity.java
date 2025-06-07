package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @NotNull
    @Column(name = "product_id", columnDefinition = "UUID")
    private UUID productId;

    @Positive
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Positive
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

}
