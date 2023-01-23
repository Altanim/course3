package com.socks.course3.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SockItem {
    private Socks socks;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }
}
