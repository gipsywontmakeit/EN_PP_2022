/*
* Nome: José Miguel Pinto de Sousa
* Número: 8200689
* Turma: LEI
*
* Nome: Ricardo Moreira da Fonte
* Número: 8200520
* Turma: LSIRC
 */
package api;

import estgconstroi.Employee;
import estgconstroi.Event;
import estgconstroi.EventManager;
import estgconstroi.Notifier;
import estgconstroi.enums.EventPriority;
import estgconstroi.exceptions.EventManagerException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author phrea
 */
public class WorkerEventManager implements EventManager {

    private static final int MAX = 100;

    private Notifier notifiers[] = new Notifier[MAX];
    private Event events[] = new Event[MAX];

    private int count = 0;

    @Override
    public void addNotifier(Notifier ntfr) throws EventManagerException {
        if (notifiers.length == MAX) {
            increaseNotifierArraySize();
        }

        this.notifiers[count] = ntfr;
        this.count++;
        System.out.println("Notifier added.");

    }

    @Override
    public void removeNotifier(Notifier ntfr) throws EventManagerException {
        if (findNotify(ntfr) != -1) {
            for (int i = findNotify(ntfr); i < count - 1; i++) {
                notifiers[i] = notifiers[i + 1];
            }
            notifiers[count - 1] = null;
            count--;
            System.out.println("Notifier removed.");
        }
    }

    @Override
    public void reportEvent(Event event) throws EventManagerException {
        if (events.length == MAX) {
            increaseEventArraySize();
        }

        this.events[count] = event;
        this.count++;
        System.out.println("Event added. ");
    }

    @Override
    public void removeAllEvents() {
        for (int i = events.length; i < 0; i--) {
            events[i] = events[events.length - 1];
        }
        if (events.length == 0) {
            System.out.println("All events removed.");
        }
    }

    @Override
    public void removeEvent(Event event) throws EventManagerException {
        if (findEvent(event) != -1) {
            for (int i = findEvent(event); i < count - 1; i++) {
                events[i] = events[i + 1];
            }
            events[count - 1] = null;
            count--;
            System.out.println("Event removed.");
        }
    }

    @Override
    public Event[] getEvent(EventPriority ep) {
        for (int i = 0; i < events.length; i++) {
            
        }
        return null;
    }

    @Override
    public Event[] getEvent(Class type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Event[] getEvent(LocalDate ld
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Event[] getEvent(LocalDate ld, LocalDate ld1
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void increaseNotifierArraySize() {
        Notifier[] temp = new Notifier[notifiers.length * 2];

        for (int i = 0; i < notifiers.length; i++) {
            temp[i] = notifiers[i];
        }
        notifiers = temp;
    }

    public void increaseEventArraySize() {
        Event[] temp = new Event[events.length * 2];

        for (int i = 0; i < events.length; i++) {
            temp[i] = events[i];
        }
        events = temp;
    }

    private int findNotify(Notifier notify) {

        if (notify == null) {
            return -1;
        }

        if (notify instanceof NotifierCollection) {

            for (int i = 0; i < count; i++) {
                if (notifiers[i].equals(notify)) {
                    return i;
                }
            }
        } else {
            return -1;
        }

        return -1;
    }

    private int findEvent(Event event) {
        count = 0;

        if (event == null) {
            return -1;
        }

        if (event instanceof WorkEvent) {
            for (int i = 0; i < count; i++) {
                if (events[i].equals(event)) {
                    return i;
                }
            }
        } else {
            return -1;
        }
        return -1;
    }
}
