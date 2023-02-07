package ru.deti.features.road

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.roads.Roads
import ru.deti.database.roads.roadsDTO
import ru.deti.database.subjects.Subjects

class RoadController {

    suspend fun insertRoad(call: ApplicationCall) {
        val roadReceiveModel = call.receive<RoadReceiveModel>()
        val subjectModel = Subjects.fetchSubject(roadReceiveModel.subject_id)
        if(subjectModel != null) {
            val roadModel = Roads.fetchRoad(roadReceiveModel.subject_id)
            if(roadModel != null) {
                Roads.deleteRoad(roadReceiveModel.subject_id)
            }
            Roads.insert(
                roadsDTO(
                    subject_id = roadReceiveModel.subject_id,
                    road = roadReceiveModel.road
                )
            )
            call.respond(HttpStatusCode.OK)
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Subject doesnt exist")
        }
    }

}