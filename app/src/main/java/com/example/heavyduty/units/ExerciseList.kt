package com.example.heavyduty.units

sealed class ExerciseList(val exerciseName: String, val exerciseType: String) {
    data object SQUATS: ExerciseList("Squats", ExerciseType.COMPOUND.exerciseType)
    data object DEADLIFTS: ExerciseList("Dead Lifts", ExerciseType.COMPOUND.exerciseType)
    data object LEGPRESS: ExerciseList("Leg Press", ExerciseType.COMPOUND.exerciseType)
    data object DIPS: ExerciseList("Dips", ExerciseType.COMPOUND.exerciseType)
    data object PALMSUPPULLDOWN: ExerciseList("Palms Up\nPull Down", ExerciseType.COMPOUND.exerciseType)
    data object BARBELLROWS: ExerciseList("Barbell Rows", ExerciseType.ISOLATION.exerciseType)
    data object PRESSBEHINDTHENECK: ExerciseList("Press Behind\nThe neck", ExerciseType.COMPOUND.exerciseType)
    data object STANDINGBARBELLCURLS: ExerciseList("Standing\nBarbbell Curls", ExerciseType.ISOLATION.exerciseType)
    data object STANDINGCALVESRAISES: ExerciseList("Standing\nCalves Raises", ExerciseType.ISOLATION.exerciseType)
    data object SITUPS: ExerciseList("Sit Ups", ExerciseType.ISOLATION.exerciseType)
    data object CABLECROSSOVERS: ExerciseList("Cable\nCross Overs", ExerciseType.ISOLATION.exerciseType)
    data object PECKDECKS: ExerciseList("Pec Decks", ExerciseType.ISOLATION.exerciseType)
    data object INCLINEPRESS: ExerciseList("Incline Press", ExerciseType.COMPOUND.exerciseType)
    data object FLATBENCHPRESS: ExerciseList("Flat Bench Press", ExerciseType.COMPOUND.exerciseType)
    data object DUMBBELLPULLOVERS: ExerciseList("Dumbbell\nPull Overs", ExerciseType.ISOLATION.exerciseType)
    data object DUMBBELLFLYES: ExerciseList("Dumbbell Flyes", ExerciseType.ISOLATION.exerciseType)
    data object STRAIGHTARMPULLDOWN: ExerciseList("Straight Arm\nPull Down", ExerciseType.ISOLATION.exerciseType)
    data object STRAIGHTARMLATMACHINEPULLDOWN: ExerciseList("Straight Arm\nLat Machine\nPull Down", ExerciseType.ISOLATION.exerciseType)
    data object TRICEPSPRESSDOWN: ExerciseList("Triceps\nPress Down", ExerciseType.ISOLATION.exerciseType)
    data object BENTOVERDUMBBELLLATERALS: ExerciseList("Bent Over\nDumbbell Laterals", ExerciseType.ISOLATION.exerciseType)
    data object DUMBBELLLATERALSRAISES: ExerciseList("Dumbbell\nLaterals Raises", ExerciseType.ISOLATION.exerciseType)
    data object ONEARMDUMBELLROWS: ExerciseList("One Arm\nDumbbell Rows", ExerciseType.ISOLATION.exerciseType)
    data object SHRUGS: ExerciseList("Shrugs", ExerciseType.ISOLATION.exerciseType)
    data object LEGCURLS: ExerciseList("Leg Curls", ExerciseType.ISOLATION.exerciseType)
    data object TOEPRESS: ExerciseList("Toe Press", ExerciseType.ISOLATION.exerciseType)
    data object DONKEYCALFRAISES: ExerciseList("Donkey\nCalf Raises", ExerciseType.ISOLATION.exerciseType)
    data object HANGINGLEGRAISES: ExerciseList("Hanging\nLeg Raises", ExerciseType.ISOLATION.exerciseType)
    data object MACHINELATERALRAISES: ExerciseList("Machine\nLateral Raises", ExerciseType.ISOLATION.exerciseType)
    data object BENTOVERCABLELATERALS: ExerciseList("Bent Over\nCable Laterals", ExerciseType.ISOLATION.exerciseType)
    data object UPRIGHTROWS: ExerciseList("Upright Rows", ExerciseType.ISOLATION.exerciseType)
    data object MACHINESHOULDERPRESS: ExerciseList("Machine\nShoulder Press", ExerciseType.COMPOUND.exerciseType)
    data object PREACHERCURLS: ExerciseList("Precher Curls", ExerciseType.ISOLATION.exerciseType)
    data object CONCENTRATIONCURLS: ExerciseList("Concentration\nBiceps Curls", ExerciseType.ISOLATION.exerciseType)
    data object MACHINECURLS: ExerciseList("Machine\nBiceps Curls", ExerciseType.ISOLATION.exerciseType)
    data object LYINGTRICEPSEXTENTION: ExerciseList("Lying\nTriceps Extenstion", ExerciseType.ISOLATION.exerciseType)
    data object MACHINETRICEPSEXTENTION: ExerciseList("Machine\nTriceps Extention", ExerciseType.ISOLATION.exerciseType)
    data object CLOSEGRIPBENCHPRESS: ExerciseList("Close Grip\nBench Press", ExerciseType.ISOLATION.exerciseType)
    data object ROWINGMACHINE: ExerciseList("Rowing Machine", ExerciseType.ISOLATION.exerciseType)
    data object LEGEXTENSTION: ExerciseList("Leg Extenstion", ExerciseType.ISOLATION.exerciseType)
    data object FRENCHPRESS: ExerciseList("French Press", ExerciseType.ISOLATION.exerciseType)

}
