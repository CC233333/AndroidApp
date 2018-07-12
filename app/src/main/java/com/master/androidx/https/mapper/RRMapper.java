package com.master.androidx.https.mapper;

import com.master.androidx.data.RR;
import com.master.androidx.https.AppException;

import io.reactivex.functions.Function;

public class RRMapper implements Function<RR, RR> {

    @Override
    public RR apply(RR rr) throws Exception {
        if (rr.isSuccessful())
            return rr;
        throw new AppException(rr.code, rr.message);
    }

}