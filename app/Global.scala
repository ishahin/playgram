import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}
import play.api.db.DB
import play.api.{Application, GlobalSettings}

/**
 * Configurazione di Squeryl come ORM
 */
object Global extends GlobalSettings {

  override def onStart(app: Application) {
    SessionFactory.concreteFactory = Some(() =>                 //#A
      Session.create(DB.getConnection()(app), new H2Adapter) )
  }
}
