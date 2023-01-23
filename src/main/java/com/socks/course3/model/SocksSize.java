package com.socks.course3.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;

public enum SocksSize {
    XS(35),
    S(36),
    SS(37),
    M(38),
    MM(39),
    MMM(40),
    L(41),
    LL(42),
    XL(43),
    XXL(44);

    @Nullable
    public static SocksSize parse(int size){
        for (SocksSize s : values()){
            if (Integer.compare(s.size, size) == 0){
                return s;
            }
        }
        return null;
    }
    private int size;
    SocksSize(int size) {
        this.size = size;
    }
@JsonValue
    public int getSize() {
        return size;
    }
}
