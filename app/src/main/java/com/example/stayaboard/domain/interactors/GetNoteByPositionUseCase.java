package com.example.stayaboard.domain.interactors;

import com.example.stayaboard.data.source.NotesRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Prakhar on 11/30/2017.
 */

public class GetNoteByPositionUseCase extends BaseUseCase<GetNoteByPositionUseCase.RequestValues, String> {

    public NotesRepository mNotesRepository;

    @Inject
    public GetNoteByPositionUseCase(NotesRepository notesRepository) {
        mNotesRepository = notesRepository;
    }

    @Override
    public rx.Observable<String> execute(GetNoteByPositionUseCase.RequestValues requestValues) {
        return mNotesRepository.getNoteByPosition(requestValues.position);
    }


    public static final class RequestValues {

        int position;
        public RequestValues(int position) {
            this.position = position;
        }
    }
}