package com.nako.wrocloveplaces.util;

import android.content.Context;

import com.nako.wrocloveplaces.R;
import com.nako.wrocloveplaces.model.Place;

import java.util.ArrayList;
import java.util.Random;

/**
 * Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak
 * <p/>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
public class PlacesGenerator {
    private static final int CATHEDRAL_ISLAND = 0;
    private static final int CENTENNIAL_HALL = 1;
    private static final int MAIN_RAILWAY_STATION = 2;
    private static final int MARKET_SQUARE = 3;
    private static final int TOWN_HALL = 4;

    private static final Random RANDOM = new Random();

    public static ArrayList<Place> getRandomPlaces(Context context, int size) {
        ArrayList<Place> places = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            places.add(getRandomPlace(context));
        }
        return places;
    }

    private static Place getRandomPlace(Context context) {
        int random = RANDOM.nextInt(5);
        return new Place(
                getPlaceName(context, random),
                getDescription(),
                getReview(),
                getRandomDrawableOfPlace(random)
        );
    }

    private static int getRandomDrawableOfPlace(int id) {
        switch (id) {
            default:
            case CATHEDRAL_ISLAND:
                return getRandomCathedralIslandDrawable();
            case CENTENNIAL_HALL:
                return getRandomCentennialHallDrawable();
            case MAIN_RAILWAY_STATION:
                return getRandomMainRailwayStationDrawable();
            case MARKET_SQUARE:
                return getRandomMarketSquareDrawable();
            case TOWN_HALL:
                return getRandomTownHallDrawable();

        }
    }

    private static int getRandomCathedralIslandDrawable() {
        switch (RANDOM.nextInt(2)) {
            default:
            case 0:
                return R.drawable.wroclaw_cathedral_island_0;
            case 1:
                return R.drawable.wroclaw_cathedral_island_1;
        }
    }

    private static int getRandomCentennialHallDrawable() {
        switch (RANDOM.nextInt(2)) {
            default:
            case 0:
                return R.drawable.wroclaw_centennial_hall0;
            case 1:
                return R.drawable.wroclaw_centennial_hall_1;
        }
    }

    private static int getRandomMainRailwayStationDrawable() {
        switch (RANDOM.nextInt(2)) {
            default:
            case 0:
                return R.drawable.wroclaw_main_railway_station_0;
            case 1:
                return R.drawable.wroclaw_main_railway_station_1;
        }
    }

    private static int getRandomMarketSquareDrawable() {
        switch (RANDOM.nextInt(2)) {
            default:
            case 0:
                return R.drawable.wroclaw_market_square_0;
            case 1:
                return R.drawable.wroclaw_market_square_1;
        }
    }

    private static int getRandomTownHallDrawable() {
        switch (RANDOM.nextInt(2)) {
            default:
            case 0:
                return R.drawable.wroclaw_town_hall_0;
            case 1:
                return R.drawable.wroclaw_town_hall_1;
        }
    }

    private static String getPlaceName(Context context, int id) {
        return context.getResources().getStringArray(R.array.places)[id];
    }

    private static String getDescription() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem. " +
                "Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam.";
    }

    private static String getReview() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem. " +
                "Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam.";
    }
}
