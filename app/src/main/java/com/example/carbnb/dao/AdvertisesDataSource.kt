package com.example.carbnb.dao

import com.example.carbnb.R
import com.example.carbnb.model.Advertise

class AdvertisesDataSource {
    companion object{

        fun createAdvertisesList(): ArrayList<Advertise> {
            val list = ArrayList<Advertise>()

            list.add(
                Advertise(
                    1,
                    "UserId",
                    "Today",
                    "BigBlack Fusca",
                    "Eyes of the face",
                    "Angry Bulls Street, 34",
                    "rent for weekends",
                    R.drawable.bigblack_fusca
                )
            )

            list.add(
                Advertise(
                    2,
                    "UserId",
                    "Today",
                    "BigBlack Fusca",
                    "Eyes of the face",
                    "Angry Bulls Street, 34",
                    "rent for weekends",
                    R.drawable.bigblack_fusca
                )
            )

            list.add(
                Advertise(
                    3,
                    "UserId",
                    "Today",
                    "BigBlack Fusca",
                    "Eyes of the face",
                    "Angry Bulls Street, 34",
                    "rent for weekends",
                    R.drawable.bigblack_fusca
                )
            )

            list.add(
                Advertise(
                    4,
                    "UserId",
                    "Today",
                    "BigBlack Fusca",
                    "Eyes of the face",
                    "Angry Bulls Street, 34",
                    "rent for weekends",
                    R.drawable.bigblack_fusca
                )
            )

            list.add(
                Advertise(
                    5,
                    "UserId",
                    "Today",
                    "BigBlack Fusca",
                    "Eyes of the face",
                    "Angry Bulls Street, 34",
                    "rent for weekends",
                    R.drawable.bigblack_fusca
                )
            )

            return list
        }

    }
}