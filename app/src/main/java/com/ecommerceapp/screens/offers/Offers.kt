package com.ecommerceapp.screens.offers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ecommerceapp.R
import com.ecommerceapp.component.AppContent

@Composable
fun OffersScreen(navController: NavHostController) {
    AppContent(navController, title = stringResource(id = R.string.offers)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val offerlist = arrayOf(
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_c_rose_cxnp-9692_400_290622.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_c_winner_cxnp-9688_400_290622.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_c_aapam_400_290622.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_c_rice-9691_400_290622.jpg"
                )
                LazyRow {
                    items(items = offerlist) { item ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp), model = item, contentDescription = "image",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                Spacer (modifier = Modifier.height(20.dp))

                val offerlistOfPrice = arrayOf(
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_bcd_m_bcd_250622_400.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_m_petstore_250622_400.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_m_health_suppliment_250622_400.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_m_babycare_250622_400.jpg"
                )
                LazyRow {
                    items(items = offerlistOfPrice) { item ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp), model = item, contentDescription = "image",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                val aofferOnSale = arrayOf(
                    "https://www.bigbasket.com/media/uploads/banner_images/hp_fom_m_bbpl-staples_460_270622_Bangalore.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/fresho_days_fnv_Bangalore_1600x460_28thJUN22.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/HP_EMF_M_WeekdayBangalore-1600x460_300622.jpeg",
                    "https://www.bigbasket.com/media/uploads/banner_images/hpc_cmc_cold-pressed_460_140622.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/t1_hp_cmc_m_pantry-essentials_1600x460_250622.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_m_bcd_big-bakery-store_1600x460_250622.jpg",
                    "https://www.bigbasket.com/media/uploads/banner_images/all_hp_m_b_h_bestofbeauty_460_2_250622.jpg"
                )
                LazyRow {
                    items(items = aofferOnSale) { item ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp), model = item, contentDescription = "image",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

            }
        }
    }
}
//https://levelup.gitconnected.com/create-an-auto-scroll-viewpager-with-transformation-and-ken-burns-effect-in-android-jetpack-compose-efdf46f2e8ed
