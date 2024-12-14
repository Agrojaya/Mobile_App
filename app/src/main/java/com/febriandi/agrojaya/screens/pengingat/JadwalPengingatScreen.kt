import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.febriandi.agrojaya.screens.alamat.components.Header
import com.febriandi.agrojaya.screens.pengingat.component.AktivitasListScreen
import com.febriandi.agrojaya.screens.pengingat.component.HorizontalCalendarView
import java.time.LocalDate

@Composable
fun JadwalAktivitasScreen(
    navController: NavController
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(navController, title = "Jadwal Aktivitas")

        HorizontalCalendarView(
            navController,
            onDateSelected = { date ->
                selectedDate = date
            }
        )

        AktivitasListScreen(
            selectedDate = selectedDate,
            onEditClicked = { id ->
                navController.navigate("tambahPengingat/$id")
            },
            onDeleteClicked = { id ->
            },
            navController = navController
        )
    }
}