package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.model.MenuItem
import com.example.ui.theme.GoldenAccent
import com.example.ui.theme.SpicyRed
import com.example.ui.theme.VegGreen
import com.example.ui.theme.WarmAmberPrimary

@Composable
fun MenuItemGridCard(
    item: MenuItem,
    onItemClick: () -> Unit,
    onQuickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .testTag("menu_item_${item.id}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Food Image with Coil AsyncImage + local fallback
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(if (item.imageUrl.isNotBlank()) item.imageUrl else item.imageRes)
                        .crossfade(true)
                        .error(if (item.imageRes != 0) item.imageRes else com.example.R.drawable.img_ilisher_bari_hero)
                        .placeholder(if (item.imageRes != 0) item.imageRes else com.example.R.drawable.img_ilisher_bari_hero)
                        .build(),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                )

                // Top badges
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (item.isChefSpecial) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = WarmAmberPrimary,
                            contentColor = Color.White
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Special",
                                    tint = GoldenAccent,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("স্পেশাল", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    // Rating pill and Share
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val context = LocalContext.current
                        Surface(
                            shape = CircleShape,
                            color = Color.Black.copy(alpha = 0.65f),
                            contentColor = Color.White,
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    val sendIntent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
                                        putExtra(android.content.Intent.EXTRA_TEXT, "দারুণ স্বাদের ${item.name} মাত্র ৳${item.price.toInt()} তে! আসুন ইলিশের বাড়ি রেস্টুরেন্টে।")
                                        type = "text/plain"
                                    }
                                    val shareIntent = android.content.Intent.createChooser(sendIntent, "Share dish via")
                                    context.startActivity(shareIntent)
                                }
                                .testTag("share_item_${item.id}")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Share",
                                tint = Color.White,
                                modifier = Modifier.padding(5.dp).size(11.dp)
                            )
                        }

                        Surface(
                            shape = CircleShape,
                            color = Color.Black.copy(alpha = 0.65f),
                            contentColor = Color.White
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = GoldenAccent,
                                    modifier = Modifier.size(11.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "${item.rating}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Food Info & Action
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Price & Add to Cart
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "৳${item.price.toInt()}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                    )

                    Button(
                        onClick = onQuickAdd,
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WarmAmberPrimary),
                        modifier = Modifier
                            .height(32.dp)
                            .testTag("add_item_button_${item.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "যোগ",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
