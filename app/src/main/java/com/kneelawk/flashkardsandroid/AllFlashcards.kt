package com.kneelawk.flashkardsandroid

import androidx.compose.ui.text.buildAnnotatedString

private val polyatomicIons = listOf(
    FlashcardSubset(
        "Polyatomic Cations",
        listOf(
            FlashcardData(
                "Mercury (I)".toAnnotatedString(),
                "Hg_2^{2+}".parseAnnotatedString()
            ),
            FlashcardData(
                "Ammonium".toAnnotatedString(),
                "NH_4^+".parseAnnotatedString()
            )
        )
    ),
    FlashcardSubset(
        "Nitrogen-Based",
        listOf(
            FlashcardData(
                "Nitrite".toAnnotatedString(),
                "NO_2^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Nitrate".toAnnotatedString(),
                "NO_3^-".parseAnnotatedString()
            )
        )
    ),
    FlashcardSubset(
        "Carbon-Based",
        listOf(
            FlashcardData(
                "Carbonite".toAnnotatedString(),
                "CO_2^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Carbonate".toAnnotatedString(),
                "CO_3^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Bicarbonite /\nHydrogen Carbonite".toAnnotatedString(),
                "HCO_2^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Bicarbonate /\nHydrogen Carbonate".toAnnotatedString(),
                "HCO_3^-".parseAnnotatedString()
            ),
        )
    ),
    FlashcardSubset(
        "Sulfur-Based",
        listOf(
            FlashcardData(
                "Sulfite".toAnnotatedString(),
                "SO_3^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Sulfate".toAnnotatedString(),
                "SO_4^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Bisulfite /\nHydrogen Sulfite".toAnnotatedString(),
                "HSO_3^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Bisulfate /\nHydrogen Sulfate".toAnnotatedString(),
                "HSO_4^-".parseAnnotatedString()
            ),
        )
    ),
    FlashcardSubset(
        "Phosphorus-Based",
        listOf(
            FlashcardData(
                "Phosphite".toAnnotatedString(),
                "PO_3^{3-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Phosphate".toAnnotatedString(),
                "PO_4^{3-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Hydrogen Phosphite".toAnnotatedString(),
                "HPO_3^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Hydrogen Phosphate".toAnnotatedString(),
                "HPO_4^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Dihydrogen Phosphite".toAnnotatedString(),
                "H_2PO_3^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Dihydrogen Phosphate".toAnnotatedString(),
                "H_2PO_4^-".parseAnnotatedString()
            ),
        )
    ),
    FlashcardSubset(
        "Halogens",
        listOf(
            FlashcardData(
                "Hypochlorite".toAnnotatedString(),
                "ClO^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Chlorite".toAnnotatedString(),
                "ClO_2^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Chlorate".toAnnotatedString(),
                "ClO_3^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Perchlorate".toAnnotatedString(),
                "ClO_4^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Hypobromite".toAnnotatedString(),
                "BrO^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Bromite".toAnnotatedString(),
                "BrO_2^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Bromate".toAnnotatedString(),
                "BrO_3^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Perbromate".toAnnotatedString(),
                "BrO_4^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Hypoiodite".toAnnotatedString(),
                "IO^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Iodite".toAnnotatedString(),
                "IO_2^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Iodate".toAnnotatedString(),
                "IO_3^-".parseAnnotatedString()
            ),
            FlashcardData(
                "Periodate".toAnnotatedString(),
                "IO_4^-".parseAnnotatedString()
            ),
        )
    ),
    FlashcardSubset(
        "Metallic Anions",
        listOf(
            FlashcardData(
                "Chromate".toAnnotatedString(),
                "CrO_4^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Dichromate".toAnnotatedString(),
                "Cr_2O_7^{2-}".parseAnnotatedString()
            ),
            FlashcardData(
                "Permanganate".toAnnotatedString(),
                "MnO_4^-".parseAnnotatedString()
            ),
        )
    )
)

val FLASHCARDS = listOf(
    FlashcardSet(
        "Polyatomic Ions",
        polyatomicIons
    )
)
