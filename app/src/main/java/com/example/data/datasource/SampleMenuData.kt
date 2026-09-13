package com.example.data.datasource

import com.example.R
import com.example.data.model.MenuCategory
import com.example.data.model.MenuItem
import com.example.data.model.PortionOption

object SampleMenuData {
    val items: List<MenuItem> = listOf(
        // Signature Ilish Items
        MenuItem(
            name = "পদ্মার খাঁটি সরিষা ইলিশ",
            price = 420.0,
            description = "তাজা পদ্মার বড় ইলিশের চাকা, কাঁচা সরিষা বাটা, খাঁটি সরিষার তেল ও কাঁচামরিচে রান্না করা ঐতিহ্যবাহী সিগনেচার পদ।",
            imageUrl = "https://images.unsplash.com/photo-1534422298391-e4f8c172dddb?w=600&auto=format&fit=crop&q=80",
            id = "ilish_01",
            nameBn = "পদ্মার খাঁটি সরিষা ইলিশ",
            nameEn = "Authentic Shorshe Ilish (Mustard Hilsa)",
            category = MenuCategory.ILISH_SPECIALS,
            rating = 4.9f,
            reviewCount = 520,
            prepTimeBn = "১৫-২০ মিনিট",
            caloriesBn = "৫২০ kcal",
            isSpicy = true,
            spiceLevelBn = "সরিষা ও কাঁচামরিচের ঝাঁজ",
            isVegetarian = false,
            isChefSpecial = true,
            imageRes = R.drawable.img_shorshe_ilish,
            portionOptions = listOf(
                PortionOption("পেটি পিস (১ জন)", "Belly Piece", 0.0),
                PortionOption("বড় গাদা ও পেটি (২ পিস)", "Large 2 Pcs", 380.0),
                PortionOption("ফ্যামিলি প্ল্যাটার (৪ পিস)", "Family 4 Pcs", 1150.0)
            )
        ),
        MenuItem(
            name = "চাঁদপুরী ইলিশ পোলাও",
            price = 450.0,
            description = "সুগন্ধি চিনিগুঁড়া চাল ও খাঁটি গাওয়া ঘিয়ে ইলিশের মাথা ও পেটির জুস দিয়ে দমে রান্না করা অপূর্ব স্বাদের রাজকীয় পোলাও।",
            imageUrl = "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=600&auto=format&fit=crop&q=80",
            id = "ilish_02",
            nameBn = "চাঁদপুরী ইলিশ পোলাও",
            nameEn = "Royal Chandpuri Ilish Polao",
            category = MenuCategory.POLAO_KHICHURI,
            rating = 4.9f,
            reviewCount = 410,
            prepTimeBn = "১৫ মিনিট",
            caloriesBn = "৬৬০ kcal",
            isSpicy = false,
            spiceLevelBn = "মৃদু সুগন্ধি",
            isVegetarian = false,
            isChefSpecial = true,
            imageRes = R.drawable.img_ilish_polao,
            portionOptions = listOf(
                PortionOption("১ জন (১ পিস ইলিশ সহ)", "Single (1 Pc Ilish)", 0.0),
                PortionOption("ডাবল প্লেটার (২ পিস ইলিশ)", "Double (2 Pcs Ilish)", 390.0)
            )
        ),
        MenuItem(
            name = "ভাপা ইলিশ কলাপাতা পাতুড়ি",
            price = 430.0,
            description = "নারকেল কোড়া, পোস্তদানা ও ঝাঁঝাঁলো হলুদ সরিষার পেস্টে কলাপাতায় মুড়ে হালকা আঁচে ভাপানো রসালো ইলিশ।",
            imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?w=600&auto=format&fit=crop&q=80",
            id = "ilish_03",
            nameBn = "ভাপা ইলিশ কলাপাতা পাতুড়ি",
            nameEn = "Steamed Bhapa Ilish Paturi",
            category = MenuCategory.ILISH_SPECIALS,
            rating = 4.8f,
            reviewCount = 280,
            prepTimeBn = "২০ মিনিট",
            caloriesBn = "৪৮০ kcal",
            isSpicy = true,
            spiceLevelBn = "মাঝারি",
            isVegetarian = false,
            isChefSpecial = true,
            imageRes = R.drawable.img_shorshe_ilish,
            portionOptions = listOf(
                PortionOption("১ পাতা (১ পিস)", "1 Paturi (1 Pc)", 0.0),
                PortionOption("২ পাতা (২ পিস)", "2 Paturi (2 Pcs)", 390.0)
            )
        ),
        MenuItem(
            name = "ইলিশের ডিম ভাজা ও শুকনো মরিচ",
            price = 260.0,
            description = "মুচমুচে সোনালী করে ভাজা আস্ত ইলিশের ডিম, কড়া ভাজা পেঁয়াজ ও মচমচে শুকনো মরিচ দিয়ে পরিবেশিত।",
            imageUrl = "https://images.unsplash.com/photo-1589302168068-964664d93dc0?w=600&auto=format&fit=crop&q=80",
            id = "ilish_04",
            nameBn = "ইলিশের ডিম ভাজা ও শুকনো মরিচ",
            nameEn = "Crispy Fried Ilish Eggs",
            category = MenuCategory.GRILL_KEBAB,
            rating = 4.9f,
            reviewCount = 310,
            prepTimeBn = "১০-১৫ মিনিট",
            caloriesBn = "৪১০ kcal",
            isSpicy = true,
            spiceLevelBn = "ঝাল ও মচমচে",
            isVegetarian = false,
            isChefSpecial = false,
            imageRes = R.drawable.img_kebab,
            portionOptions = listOf(
                PortionOption("১ প্লেট (২ পিস ডিম)", "1 Plate (2 Pcs)", 0.0),
                PortionOption("ডাবল প্লেট (৪ পিস ডিম)", "Double (4 Pcs)", 240.0)
            )
        ),
        MenuItem(
            name = "স্পেশাল ইলিশ বাড়ি কম্বো থালি",
            price = 599.0,
            description = "সুগন্ধি গরম ভাত, ১ পিস বড় সরিষা ইলিশ, ১ পিস কড়া ভাজা ইলিশ, লেজ ভর্তা, ইলিশের ডিম, মুগ ডাল ও বেগুন ভাজা।",
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
            id = "thali_01",
            nameBn = "স্পেশাল ইলিশ বাড়ি কম্বো থালি",
            nameEn = "Grand Ilish Bari Combo Thali",
            category = MenuCategory.THALI_COMBO,
            rating = 5.0f,
            reviewCount = 680,
            prepTimeBn = "১৫ মিনিট",
            caloriesBn = "৮৯০ kcal",
            isSpicy = true,
            spiceLevelBn = "ঐতিহ্যবাহী",
            isVegetarian = false,
            isChefSpecial = true,
            imageRes = R.drawable.img_ilisher_bari_hero,
            portionOptions = listOf(
                PortionOption("থালি ১ জন", "Thali Single", 0.0),
                PortionOption("যুগল থালি (২ জন)", "Couple Thali (2 Persons)", 520.0)
            )
        ),
        MenuItem(
            name = "পদ্মা ইলিশের লেজ ভর্তা",
            price = 140.0,
            description = "কাঁটা নিখুঁতভাবে বেছে খাঁটি সরিষার তেল, কাঁচা পেঁয়াজ, ধনেপাতা ও কড়া ভাজা লাল মরিচ দিয়ে হাতে মাখা সুস্বাদু লেজ ভর্তা।",
            imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=600&auto=format&fit=crop&q=80",
            id = "bhorta_01",
            nameBn = "পদ্মা ইলিশের লেজ ভর্তা",
            nameEn = "Spicy Ilish Lej Bhorta",
            category = MenuCategory.CURRY_BHORTA,
            rating = 4.8f,
            reviewCount = 490,
            prepTimeBn = "১০ মিনিট",
            caloriesBn = "২২০ kcal",
            isSpicy = true,
            spiceLevelBn = "ঝাল ও মুখরোচক",
            isVegetarian = false,
            isChefSpecial = true,
            imageRes = R.drawable.img_kebab,
            portionOptions = listOf(
                PortionOption("১ বাটি (১ জন)", "1 Bowl (1 Person)", 0.0),
                PortionOption("বড় বাটি (২ জন)", "Large Bowl (2 Persons)", 110.0)
            )
        ),
        MenuItem(
            name = "ইলিশ মাছ ভুনা ও ডাল চচ্চড়ি",
            price = 390.0,
            description = "টমেটো, পেঁয়াজ কুচি ও কাঁচামরিচে কষা কষা করে ভুনা করা ইলিশ মাছ। সাথে ঘন রসুনি মসুর ডাল।",
            imageUrl = "https://images.unsplash.com/photo-1512058564366-18510be2db19?w=600&auto=format&fit=crop&q=80",
            id = "curry_01",
            nameBn = "ইলিশ মাছ ভুনা ও ডাল চচ্চড়ি",
            nameEn = "Spicy Ilish Fish Bhuna Curry",
            category = MenuCategory.CURRY_BHORTA,
            rating = 4.7f,
            reviewCount = 210,
            prepTimeBn = "১৫ মিনিট",
            caloriesBn = "৫১০ kcal",
            isSpicy = true,
            spiceLevelBn = "মাঝারি ঝাল",
            isVegetarian = false,
            isChefSpecial = false,
            imageRes = R.drawable.img_shorshe_ilish,
            portionOptions = listOf(
                PortionOption("১ পিস বড় চাকা", "1 Large Steak", 0.0),
                PortionOption("২ পিস চাকা", "2 Steaks", 350.0)
            )
        ),
        MenuItem(
            name = "দই ইলিশ শাহী কারি",
            price = 440.0,
            description = "মিষ্টি টক দই, কাজুবাদাম বাটা ও হালকা গরম মসলায় রান্না করা মসৃণ ও মুখরোচক শাহী ইলিশ কারি।",
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
            id = "curry_02",
            nameBn = "দই ইলিশ শাহী কারি",
            nameEn = "Royal Shahi Doi Ilish",
            category = MenuCategory.CURRY_BHORTA,
            rating = 4.8f,
            reviewCount = 175,
            prepTimeBn = "১৫-২০ মিনিট",
            caloriesBn = "৫৮০ kcal",
            isSpicy = false,
            spiceLevelBn = "মৃদু মিষ্টি-ঝাল",
            isVegetarian = false,
            isChefSpecial = true,
            imageRes = R.drawable.img_shorshe_ilish,
            portionOptions = listOf(
                PortionOption("১ পিস পেটি", "1 Belly Piece", 0.0),
                PortionOption("২ পিস পেটি", "2 Belly Pieces", 390.0)
            )
        ),
        MenuItem(
            name = "ইলিশ ভুনা খিচুড়ি স্পেশাল",
            price = 420.0,
            description = "সোনা মুগ ডালের ঝরঝরে খিচুড়ির সাথে কড়া ভাজা ১ পিস বড় ইলিশ মাছ ও ডিম ভাজা।",
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
            id = "khichuri_01",
            nameBn = "ইলিশ ভুনা খিচুড়ি স্পেশাল",
            nameEn = "Monsoon Ilish Khichuri Platter",
            category = MenuCategory.POLAO_KHICHURI,
            rating = 4.9f,
            reviewCount = 430,
            prepTimeBn = "১৫ মিনিট",
            caloriesBn = "৭২০ kcal",
            isSpicy = false,
            spiceLevelBn = "স্বাভাবিক",
            isVegetarian = false,
            isChefSpecial = true,
            imageRes = R.drawable.img_ilish_polao,
            portionOptions = listOf(
                PortionOption("১ প্লেট ফুল", "Full Plate", 0.0),
                PortionOption("ডাবল প্লেটার", "Double Platter", 380.0)
            )
        ),
        MenuItem(
            name = "মচমচে ইলিশ ফ্রাই ও গোল আলু ভাজা",
            price = 370.0,
            description = "কাঁচা হলুদ ও মরিচে ম্যারিনেট করে গরম ডুবো তেলে ভাজা মচমচে ইলিশ মাছ ও পাতলা চিপস স্টাইল আলু ভাজা।",
            imageUrl = "https://images.unsplash.com/photo-1534422298391-e4f8c172dddb?w=600&auto=format&fit=crop&q=80",
            id = "fry_01",
            nameBn = "মচমচে ইলিশ ফ্রাই ও গোল আলু ভাজা",
            nameEn = "Crispy Fried Ilish with Potatoes",
            category = MenuCategory.GRILL_KEBAB,
            rating = 4.8f,
            reviewCount = 260,
            prepTimeBn = "১০-১২ মিনিট",
            caloriesBn = "৪৫০ kcal",
            isSpicy = true,
            spiceLevelBn = "মচমচে ঝাল",
            isVegetarian = false,
            isChefSpecial = false,
            imageRes = R.drawable.img_kebab,
            portionOptions = listOf(
                PortionOption("১ পিস ফ্রাই", "1 Pc Fry", 0.0),
                PortionOption("২ পিস ফ্রাই", "2 Pcs Fry", 330.0)
            )
        ),
        MenuItem(
            name = "ঐতিহ্যবাহী বোরহানি ও পুদিনা শরবত",
            price = 90.0,
            description = "টক দই, পুদিনা পাতা, বিট লবণ ও হজমি মশলার সমন্বয়ে তৈরি সতেজকারক ঠান্ডা বোরহানি।",
            imageUrl = "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=600&auto=format&fit=crop&q=80",
            id = "drinks_01",
            nameBn = "ঐতিহ্যবাহী বোরহানি",
            nameEn = "Traditional Royal Borhani",
            category = MenuCategory.BEVERAGES_DESSERTS,
            rating = 4.8f,
            reviewCount = 310,
            prepTimeBn = "৫ মিনিট",
            caloriesBn = "১২০ kcal",
            isSpicy = false,
            spiceLevelBn = "হজমি মশলাদার",
            isVegetarian = true,
            isChefSpecial = false,
            imageRes = R.drawable.img_restaurant_hero,
            portionOptions = listOf(
                PortionOption("১ গ্লাস (২৫০ মিলি)", "1 Glass (250ml)", 0.0),
                PortionOption("১ লিটার ফ্যামিলি বোতল", "1 Litre Family Bottle", 180.0)
            )
        ),
        MenuItem(
            name = "চাঁদপুরী খাঁটি মিষ্টি দই ও রসগোল্লা",
            price = 120.0,
            description = "মাটির পাত্রে জমাট বাঁধা গাঢ় মিষ্টি দই ও গরম ২টি নরম রসগোল্লা। ভোজ শেষে মিষ্টিমুখের সেরা তৃপ্তি।",
            imageUrl = "https://images.unsplash.com/photo-1551024709-8f23befc6f87?w=600&auto=format&fit=crop&q=80",
            id = "dessert_01",
            nameBn = "চাঁদপুরী মিষ্টি দই ও রসগোল্লা",
            nameEn = "Clay Pot Mishti Doi & Rasgulla",
            category = MenuCategory.BEVERAGES_DESSERTS,
            rating = 4.9f,
            reviewCount = 270,
            prepTimeBn = "৫ মিনিট",
            caloriesBn = "২৮০ kcal",
            isSpicy = false,
            spiceLevelBn = "মিষ্টি",
            isVegetarian = true,
            isChefSpecial = false,
            imageRes = R.drawable.img_biryani,
            portionOptions = listOf(
                PortionOption("১ কাপ + ২ মিষ্টি", "Single Cup + 2 Sweets", 0.0),
                PortionOption("হাফ কেজি মাটির হাড়ি", "Half KG Clay Pot", 180.0)
            )
        )
    )
}
