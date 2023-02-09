package ru.deti

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database
import ru.deti.features.addChildren.configureAddChildrenRouting
import ru.deti.features.children.login_children.configureLoginChildrenRouting
import ru.deti.features.children.register_children.configureRegisterChildrenRouting
import ru.deti.features.notification.configureNotificationsRouting
import ru.deti.features.parent.login_parent.configureLoginParentRouting
import ru.deti.features.parent.register_parent.configureRegisterParentRouting
import ru.deti.features.photos.configurePhotosRouting
import ru.deti.features.qrs.configureQRsRouting
import ru.deti.features.readingProfiles.configureReadingRouting
import ru.deti.features.road.configureRoadsRouting
import ru.deti.features.subject.configureSubjectRouting

fun main() {
    val PGUSER = "postgres"
    val PGPASSWORD = "WDQY9pPa1ywBGt7mP867"
    val PGHOST = "containers-us-west-164.railway.app"
    val PGPORT = "5622"
    val PGDATABASE = "railway"

    Database.connect(
        "jdbc:postgresql://${PGHOST}:${PGPORT}/${PGDATABASE}", driver = "org.postgresql.Driver",
        "${PGUSER}", "${PGPASSWORD}"
    )

    embeddedServer(CIO,
        port = 8080,
        module = Application::myApplicationModule
    ).start(wait = true)
}

fun Application.myApplicationModule() {
    installSerialization()

    configureStartingRouting()

    configureRegisterParentRouting()
    configureLoginParentRouting()
    configureAddChildrenRouting()

    configureRegisterChildrenRouting()
    configureLoginChildrenRouting()

    configurePhotosRouting()

    configureQRsRouting()

    configureReadingRouting()

    configureSubjectRouting()
    configureNotificationsRouting()
    configureRoadsRouting()
}
