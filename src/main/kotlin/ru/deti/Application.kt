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

    Database.connect(
        "jdbc:postgresql://localhost:5432/deti", driver = "org.postgresql.Driver",
        "postgres", "katanapas"
    )

    embeddedServer(CIO,
        port = 8080,
        host = "0.0.0.0",
        module = Application::myApplicationModule
    ).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation)
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
