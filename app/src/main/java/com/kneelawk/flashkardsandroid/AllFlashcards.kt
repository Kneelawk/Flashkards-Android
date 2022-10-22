package com.kneelawk.flashkardsandroid

import androidx.compose.ui.text.buildAnnotatedString

private val polyatomicIons = listOf(
    FlashcardSubset(
        "Polyatomic Cations",
        listOf(
            FlashcardData(
                "Mercury (I)".toAnnotatedString(),
                buildAnnotatedString {
                    append("Hg")
                    append("2", Subscript)
                    append("2+", Superscript)
                }
            ),
            FlashcardData(
                "Ammonium".toAnnotatedString(),
                buildAnnotatedString {
                    append("NH")
                    append("4", Subscript)
                    append("+", Superscript)
                }
            )
        )
    ),
    FlashcardSubset(
        "Nitrogen-Based",
        listOf(
            FlashcardData(
                "Nitrite".toAnnotatedString(),
                buildAnnotatedString {
                    append("NO")
                    append("2", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Nitrate".toAnnotatedString(),
                buildAnnotatedString {
                    append("NO")
                    append("3", Subscript)
                    append("-", Superscript)
                }
            )
        )
    ),
    FlashcardSubset(
        "Carbon-Based",
        listOf(
            FlashcardData(
                "Carbonite".toAnnotatedString(),
                buildAnnotatedString {
                    append("CO")
                    append("2", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Carbonate".toAnnotatedString(),
                buildAnnotatedString {
                    append("CO")
                    append("3", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Bicarbonite /\nHydrogen Carbonite".toAnnotatedString(),
                buildAnnotatedString {
                    append("HCO")
                    append("2", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Bicarbonate /\nHydrogen Carbonate".toAnnotatedString(),
                buildAnnotatedString {
                    append("HCO")
                    append("3", Subscript)
                    append("-", Superscript)
                }
            ),
        )
    ),
    FlashcardSubset(
        "Sulfur-Based",
        listOf(
            FlashcardData(
                "Sulfite".toAnnotatedString(),
                buildAnnotatedString {
                    append("SO")
                    append("3", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Sulfate".toAnnotatedString(),
                buildAnnotatedString {
                    append("SO")
                    append("4", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Bisulfite /\nHydrogen Sulfite".toAnnotatedString(),
                buildAnnotatedString {
                    append("HSO")
                    append("3", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Bisulfate /\nHydrogen Sulfate".toAnnotatedString(),
                buildAnnotatedString {
                    append("HSO")
                    append("4", Subscript)
                    append("-", Superscript)
                }
            ),
        )
    ),
    FlashcardSubset(
        "Phosphorus-Based",
        listOf(
            FlashcardData(
                "Phosphite".toAnnotatedString(),
                buildAnnotatedString {
                    append("PO")
                    append("3", Subscript)
                    append("3-", Superscript)
                }
            ),
            FlashcardData(
                "Phosphate".toAnnotatedString(),
                buildAnnotatedString {
                    append("PO")
                    append("4", Subscript)
                    append("3-", Superscript)
                }
            ),
            FlashcardData(
                "Hydrogen Phosphite".toAnnotatedString(),
                buildAnnotatedString {
                    append("HPO")
                    append("3", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Hydrogen Phosphate".toAnnotatedString(),
                buildAnnotatedString {
                    append("HPO")
                    append("4", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Dihydrogen Phosphite".toAnnotatedString(),
                buildAnnotatedString {
                    append("H")
                    append("2", Subscript)
                    append("PO")
                    append("3", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Dihydrogen Phosphate".toAnnotatedString(),
                buildAnnotatedString {
                    append("H")
                    append("2", Subscript)
                    append("PO")
                    append("4", Subscript)
                    append("-", Superscript)
                }
            ),
        )
    ),
    FlashcardSubset(
        "Halogens",
        listOf(
            FlashcardData(
                "Hypochlorite".toAnnotatedString(),
                buildAnnotatedString {
                    append("ClO")
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Chlorite".toAnnotatedString(),
                buildAnnotatedString {
                    append("ClO")
                    append("2", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Chlorate".toAnnotatedString(),
                buildAnnotatedString {
                    append("ClO")
                    append("3", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Perchlorate".toAnnotatedString(),
                buildAnnotatedString {
                    append("ClO")
                    append("4", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Hypobromite".toAnnotatedString(),
                buildAnnotatedString {
                    append("BrO")
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Bromite".toAnnotatedString(),
                buildAnnotatedString {
                    append("BrO")
                    append("2", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Bromate".toAnnotatedString(),
                buildAnnotatedString {
                    append("BrO")
                    append("3", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Perbromate".toAnnotatedString(),
                buildAnnotatedString {
                    append("BrO")
                    append("4", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Hypoiodite".toAnnotatedString(),
                buildAnnotatedString {
                    append("IO")
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Iodite".toAnnotatedString(),
                buildAnnotatedString {
                    append("IO")
                    append("2", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Iodate".toAnnotatedString(),
                buildAnnotatedString {
                    append("IO")
                    append("3", Subscript)
                    append("-", Superscript)
                }
            ),
            FlashcardData(
                "Periodate".toAnnotatedString(),
                buildAnnotatedString {
                    append("IO")
                    append("4", Subscript)
                    append("-", Superscript)
                }
            ),
        )
    ),
    FlashcardSubset(
        "Metallic Anions",
        listOf(
            FlashcardData(
                "Chromate".toAnnotatedString(),
                buildAnnotatedString {
                    append("CrO")
                    append("4", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Dichromate".toAnnotatedString(),
                buildAnnotatedString {
                    append("Cr")
                    append("2", Subscript)
                    append("O")
                    append("7", Subscript)
                    append("2-", Superscript)
                }
            ),
            FlashcardData(
                "Permanganate".toAnnotatedString(),
                buildAnnotatedString {
                    append("MnO")
                    append("4", Subscript)
                    append("-", Superscript)
                }
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
