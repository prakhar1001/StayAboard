package com.example.stayabode.domain.interactors.base_use_cases;

/**
 * Created by Prakhar on 11/30/2017.
 */

public abstract class BaseUseCaseNoReturn<REQUEST_DATA> {

    public abstract void execute(REQUEST_DATA requestData);

}
