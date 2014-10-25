package com.example.gridviewimplementation;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

public class Animal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String title;
	private String details;
	private int mData;
	
	public Animal(String title, String det){
		this.setTitle(title);
		this.setDetails(det);
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	private void setDetails(String details) {
		this.details = details;
	}
	
	public static int getAnimalPictureSource(Animal animal){
		String title = animal.getTitle();
		
		if (title == "Cat") {
			return R.drawable.cat;
		}
		else if (title == "Dog") {
			return R.drawable.dog;
		}
		else if (title == "Horse") {
			return R.drawable.horse;
		}
		else if (title == "Lion") {
			return R.drawable.lion;
		}
		else if (title == "Eagle") {
			return R.drawable.eagle;
		}
		else if (title == "Tiger") {
			return R.drawable.tiger;
		}
		else if (title == "Elephant") {
			return R.drawable.elephant;
		}
		else if (title == "Dolphin") {
			return R.drawable.dolphin;
		}
		else if (title == "Wolf") {
			return R.drawable.wolf;
		}
		else {
			return R.drawable.animals;
		}
	}

//	@Override
//	public int describeContents() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		// TODO Auto-generated method stub
//		dest.writeInt(mData);
//	}
//	
//	public static final Parcelable.Creator<Animal> CREATOR = new Parcelable.Creator<Animal>() {
//        public Animal createFromParcel(Parcel in) {
//            return new Animal(in);
//        }
//
//        public Animal[] newArray(int size) {
//            return new Animal[size];
//        }
//    };
//
//    // example constructor that takes a Parcel and gives you an object populated with it's values
//    private Animal(Parcel in) {
//        mData = in.readInt();
//    }
}
