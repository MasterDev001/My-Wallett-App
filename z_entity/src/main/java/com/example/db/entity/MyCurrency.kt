
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.db.remote_models.CurrencyRemote

@Entity(tableName = "currencies")
data class MyCurrency(
    @PrimaryKey
    val id: String,
    var name: String,
    var rate: Double,
    var date: Long,
    var uploaded: Boolean = false
) {
    fun toRemote() = CurrencyRemote(
        id = this.id,
        name = this.name,
        rate = this.rate,
        date = this.date
    )
}
