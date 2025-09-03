package com.example.complexhomepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import complexhomepage.composeapp.generated.resources.Res
import complexhomepage.composeapp.generated.resources.ic_category
import complexhomepage.composeapp.generated.resources.ic_down
import complexhomepage.composeapp.generated.resources.ic_graph
import complexhomepage.composeapp.generated.resources.ic_home_filled
import complexhomepage.composeapp.generated.resources.ic_location_filled
import complexhomepage.composeapp.generated.resources.ic_menu
import complexhomepage.composeapp.generated.resources.ic_message
import complexhomepage.composeapp.generated.resources.ic_notification
import complexhomepage.composeapp.generated.resources.ic_pin
import complexhomepage.composeapp.generated.resources.ic_profile
import complexhomepage.composeapp.generated.resources.ic_pushup_woman
import complexhomepage.composeapp.generated.resources.ic_search
import complexhomepage.composeapp.generated.resources.ic_star
import complexhomepage.composeapp.generated.resources.ic_time
import complexhomepage.composeapp.generated.resources.ic_workout_woman

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(color = Color.White) {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomNavBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding) // 避免内容被底部栏遮挡
                .verticalScroll(rememberScrollState())
        ) {
            StatusBar()
            TopNavBar()

            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xFF303437),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                )

                UpcomingSection()
                GymsNearYouSection()
                ActivitiesSection()

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
private fun StatusBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "9:41",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF090A0A),
                fontWeight = FontWeight.Medium
            )
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mobile Signal, WiFi, Battery icons would go here
            // For now, using placeholder boxes
            Box(
                modifier = Modifier
                    .size(18.dp, 10.dp)
                    .background(Color(0xFF090A0A), RoundedCornerShape(2.dp))
            )
            Box(
                modifier = Modifier
                    .size(15.dp, 11.dp)
                    .background(Color(0xFF090A0A), RoundedCornerShape(2.dp))
            )
            Box(
                modifier = Modifier
                    .size(27.dp, 13.dp)
                    .background(Color(0xFF090A0A), RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
private fun TopNavBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Categories Button
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(44.dp)
                .background(Color.White, CircleShape)
                .border(1.dp, Color(0xFFE3E5E5), CircleShape)
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_category),
                contentDescription = "Categories",
                modifier = Modifier.size(24.dp)
            )
        }
        
        // Right side buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(44.dp)
                    .background(Color.White, CircleShape)
                    .border(1.dp, Color(0xFFE3E5E5), CircleShape)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_message),
                    contentDescription = "Messages",
                    modifier = Modifier.size(24.dp)
                )
            }
            
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(44.dp)
                    .background(Color.White, CircleShape)
                    .border(1.dp, Color(0xFFE3E5E5), CircleShape)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_notification),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun UpcomingSection() {
    Column {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Upcoming",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color(0xFF404446),
                    fontWeight = FontWeight.Bold
                )
            )
            
            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(Res.drawable.ic_menu),
                    contentDescription = "Menu",
                    modifier = Modifier.size(24.dp).alpha(0f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Cards Row
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(upcomingActivities) { activity ->
                UpcomingCard(activity = activity)
            }
        }
    }
}

@Composable
private fun UpcomingCard(activity: UpcomingActivity) {
    Card(
        modifier = Modifier
            .width(292.dp)
            .height(171.dp),
        colors = CardDefaults.cardColors(containerColor = activity.backgroundColor),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Content
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                // Title and Chip
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = activity.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color(0xFF202325),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(48.dp)
                    ) {
                        Text(
                            text = activity.chipText,
                            color = activity.chipColor,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.4.sp
                            )
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = activity.time,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF404446),
                        fontWeight = FontWeight.Medium
                    )
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Location
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_location_filled),
                        contentDescription = "Location",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = activity.location,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF6C7072),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
            
            // Illustration
            Image(
                painter = painterResource(activity.illustration),
                contentDescription = activity.title,
                modifier = Modifier
                    .size(87.dp,146.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = -50.dp, y=36.dp)
            )
        }
    }
}

@Composable
private fun GymsNearYouSection() {
    Column(
        modifier = Modifier.padding(top = 32.dp)
    ) {
        // Section Header
        Text(
            text = "Gyms near you",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFF404446),
                fontWeight = FontWeight.Bold
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Location
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_location_filled),
                contentDescription = "Location",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Starowiślna 12",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF72777A),
                    fontWeight = FontWeight.Medium
                )
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Gym Cards
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(gyms) { gym ->
                GymCard(gym = gym)
            }
        }
    }
}

@Composable
private fun GymCard(gym: Gym) {
    Card(
        modifier = Modifier
            .width(187.dp)
            .height(148.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {
            Text(
                text = gym.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF202325),
                    fontWeight = FontWeight.Bold
                )
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Info
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Opening Hours
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_time),
                        contentDescription = "Time",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = gym.openingHours,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF72777A),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                
                // Distance
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_pin),
                        contentDescription = "Location",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = gym.distance,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF72777A),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                
                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_star),
                        contentDescription = "Rating",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = gym.rating,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF72777A),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ActivitiesSection() {
    Column(
        modifier = Modifier.padding(top = 32.dp)
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Activities",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color(0xFF404446),
                    fontWeight = FontWeight.Bold
                )
            )
            
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF4FF)),
                shape = RoundedCornerShape(48.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Weekly",
                        color = Color(0xFF0070F0),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(Res.drawable.ic_down),
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Activity Graph
        ActivityGraph()
    }
}

@Composable
private fun ActivityGraph() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(194.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Hours labels
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.width(30.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("5h", "4h", "3h", "2h", "1h").forEach { hour ->
                        Text(
                            text = hour,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFF72777A),
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(24.dp))
                
                // Graph area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    // Graph lines would go here
                    // For now, just a placeholder
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                    )
                    
                    // Current time indicator
                    Card(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = 50.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "3h 14min",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFF303437),
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Days of the week
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (day == "Wed") Color(0xFF0070F0) else Color(0xFF72777A),
                            fontWeight = if (day == "Wed") FontWeight.Bold else FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home (Active)
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF303437)),
                shape = RoundedCornerShape(48.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_home_filled),
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Home",
                        color = Color(0xFFF2F4F5),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
            
            // Other icons
            Image(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp)
            )
            Image(
                painter = painterResource(Res.drawable.ic_graph),
                contentDescription = "Graph",
                modifier = Modifier.size(24.dp)
            )
            Image(
                painter = painterResource(Res.drawable.ic_profile),
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

// Data classes
data class UpcomingActivity(
    val title: String,
    val chipText: String,
    val chipColor: Color,
    val time: String,
    val location: String,
    val backgroundColor: Color,
    val illustration: org.jetbrains.compose.resources.DrawableResource
)

data class Gym(
    val name: String,
    val openingHours: String,
    val distance: String,
    val rating: String
)

// Sample data
private val upcomingActivities = listOf(
    UpcomingActivity(
        title = "Workout",
        chipText = "FITNESS",
        chipColor = Color(0xFF5555CB),
        time = "Today at 2:45 PM",
        location = "Aleja Pokoju",
        backgroundColor = Color(0xFFF0F0FF),
        illustration = Res.drawable.ic_workout_woman
    ),
    UpcomingActivity(
        title = "Push-ups",
        chipText = "WARM-UP",
        chipColor = Color(0xFFA05E03),
        time = "Tomorrow at 08:00 AM",
        location = "Aleja Pokoju",
        backgroundColor = Color(0xFFFFF9F0),
        illustration = Res.drawable.ic_pushup_woman
    )
)

private val gyms = listOf(
    Gym(
        name = "GymFit",
        openingHours = "10:00 AM – 11:00 PM",
        distance = "250 meters",
        rating = "5.0"
    ),
    Gym(
        name = "Lotus",
        openingHours = "24/7",
        distance = "630 meters",
        rating = "5.0"
    )
)