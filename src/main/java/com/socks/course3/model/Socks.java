package com.socks.course3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Socks {
    private SocksColor color;
    private int cotton;
    private SocksSize size;

    public Socks(SocksColor color, int cotton, SocksSize size) {
        this.color = color;
        this.cotton = cotton;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return color == socks.getColor() && cotton == socks.getCotton() && size == socks.getSize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cotton);
    }


}
