package com.marti.dev.egzaminator.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by marcin on 30.09.2017.
 */

public class TestModel implements Parcelable {

    public static final String NAME = "TestModel";

    public List<Question> questions;

    protected TestModel(Parcel in) {
        questions = in.createTypedArrayList(Question.CREATOR);
    }

    public static final Creator<TestModel> CREATOR = new Creator<TestModel>() {
        @Override
        public TestModel createFromParcel(Parcel in) {
            return new TestModel(in);
        }

        @Override
        public TestModel[] newArray(int size) {
            return new TestModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(questions);
    }


    public static class Question implements Parcelable {

        public static class Answers implements Parcelable{
            public int id;
            public String answerText;

            protected Answers(Parcel in) {
                id = in.readInt();
                answerText = in.readString();
            }

            public static final Creator<Answers> CREATOR = new Creator<Answers>() {
                @Override
                public Answers createFromParcel(Parcel in) {
                    return new Answers(in);
                }

                @Override
                public Answers[] newArray(int size) {
                    return new Answers[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(id);
                parcel.writeString(answerText);
            }
        }

        public int id;
        public Integer possibleAnswers;
        public String questionText;
        public Integer correctAnswer;
        public String imagePath;
        public List<Answers> answers;



        protected Question(Parcel in) {
            id = in.readInt();
            possibleAnswers = in.readInt();
            questionText = in.readString();
            correctAnswer = in.readInt();
            imagePath = in.readString();
            answers = in.createTypedArrayList(Answers.CREATOR);
        }

        public static final Creator<Question> CREATOR = new Creator<Question>() {
            @Override
            public Question createFromParcel(Parcel in) {
                return new Question(in);
            }

            @Override
            public Question[] newArray(int size) {
                return new Question[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeInt(possibleAnswers);
            parcel.writeString(questionText);
            parcel.writeInt(correctAnswer);
            parcel.writeString(imagePath);
            parcel.writeTypedList(answers);
        }

    }

}
