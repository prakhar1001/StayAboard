package com.example.stayaboard.presentation.screens.edit_note;

import android.util.Log;

import com.example.stayaboard.data.models.NoteItem;
import com.example.stayaboard.data.source.NotesRepository;
import com.example.stayaboard.domain.interactors.EditNoteUseCase;
import com.example.stayaboard.domain.interactors.GetNoteByPositionUseCase;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Prakhar on 11/30/2017.
 */

public class EditNotePresenter implements EditNoteContract.Presenter {

    EditNoteContract.View mView;

    private final Scheduler threadScheduler;
    private final Scheduler postExecutionScheduler;
    EditNoteUseCase mEditNoteUseCase;
    String mSavedNoteBody;

    @Inject
    public EditNotePresenter(@Named("Thread") Scheduler threadScheduler,
                             @Named("PostExecution") Scheduler postExecutionScheduler,
                             EditNoteUseCase editNoteUseCase) {
        this.threadScheduler = threadScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
        mEditNoteUseCase = editNoteUseCase;
    }

    public void attachView(EditNoteContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void handleEditDoneButtonClicked(int position, final String noteBody) {


        mEditNoteUseCase.execute(new EditNoteUseCase.RequestValues(noteBody, position))
                .subscribeOn(threadScheduler)
                .observeOn(postExecutionScheduler)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("tag", "completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("tag", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Boolean edited) {
                        if (edited) {
                            mView.doWhenNoteIsChanged();
                        } else {
                            mView.doWhenNoteIsNotChanged();
                        }
                    }

                });


    }
}
