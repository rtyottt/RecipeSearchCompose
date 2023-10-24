package com.example.recipesearch


data class RecipeResult(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)

data class Links(
    val next: Next
)

data class Hit(
    val _links: LinksX,
    val recipe: Recipe
)

data class Next(
    val href: String,
    val title: String
)

data class LinksX(
    val self: Self
)
data class Recipe(
    val calories: Double,
    val image: String,
    val ingredients: List<Ingredient>,
    val label: String,
    val shareAs: String,
    val source: String,
    val totalTime: Double,
    val totalWeight: Double,
    val uri: String,
    val url: String,
    val yield: Int
)

data class Self(
    val href: String,
    val title: String
)

data class Digest(
    val daily: Double,
    val hasRDI: Boolean,
    val label: String,
    val schemaOrgTag: String,
    val sub: List<Sub>,
    val tag: String,
    val total: Double,
    val unit: String
)

data class Images(
    val LARGE: LARGE,
    val REGULAR: REGULAR,
    val SMALL: SMALL,
    val THUMBNAIL: THUMBNAIL
)

data class Ingredient(
    val food: String,
    val foodCategory: String,
    val foodId: String,
    val image: String,
    val measure: String,
    val quantity: Double,
    val text: String,
    val weight: Double
)


data class Sub(
    val daily: Double,
    val hasRDI: Boolean,
    val label: String,
    val schemaOrgTag: String,
    val tag: String,
    val total: Double,
    val unit: String
)

data class LARGE(
    val height: Int,
    val url: String,
    val width: Int
)

data class REGULAR(
    val height: Int,
    val url: String,
    val width: Int
)

data class SMALL(
    val height: Int,
    val url: String,
    val width: Int
)

data class THUMBNAIL(
    val height: Int,
    val url: String,
    val width: Int
)

data class CA(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class CHOCDF(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class CHOLE(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class ENERCKCAL(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FASAT(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FAT(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FE(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FIBTG(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FOLDFE(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class K(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class MG(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class NA(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class NIA(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class P(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class PROCNT(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class RIBF(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class THIA(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class TOCPHA(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class VITARAE(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class VITB12(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class VITB6A(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class VITC(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class VITD(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class VITK1(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class ZN(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class CHOCDFNet(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FAMS(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FAPU(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FATRN(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FOLAC(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class FOLFD(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class SUGAR(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class SUGARAdded(
    val label: String,
    val quantity: Double,
    val unit: String
)

data class SugarAlcohol(
    val label: String,
    val quantity: Int,
    val unit: String
)

data class WATER(
    val label: String,
    val quantity: Double,
    val unit: String
)