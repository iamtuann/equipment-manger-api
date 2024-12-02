package com.vn.equipment_manager.model.request;

import com.vn.equipment_manager.model.ImportReceiptItem;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportReceiptRequest {
    private Long id;
    private String code;
    private Date importDate;
    private String note;
    private List<ImportReceiptItem> items;
    private Double totalAmount;

    public Double getTotalAmount() {
        double total = 0;
        if (this.items != null) {
            total = this.items.stream()
                    .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                    .sum();
        } else {
            total = 0.0;
        }
        return total;
    }
}
