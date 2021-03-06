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

import estgconstroi.*;
import estgconstroi.enums.EventPriority;
import estgconstroi.exceptions.EventManagerException;

import java.time.LocalDate;

/**
 *
 * @author phrea
 */
public class WorkerEventManager implements EventManager {

    private static final int MAX = 100;

    private Notifier notifiers[] = new Notifier[MAX];
    private Event events[] = new Event[MAX];

    private int countEvents = 0;

    private int countNotifiers = 0;

    @Override
    public void addNotifier(Notifier ntfr) throws EventManagerException {
        if (notifiers.length == MAX) {
            increaseNotifierArraySize();
        }

        this.notifiers[countNotifiers] = ntfr;
        this.countNotifiers++;
        System.out.println("Notifier added.");

    }

    @Override
    public void removeNotifier(Notifier ntfr) throws EventManagerException {
        if (findNotify(ntfr) != -1) {
            for (int i = findNotify(ntfr); i < countNotifiers - 1; i++) {
                notifiers[i] = notifiers[i + 1];
            }
            notifiers[countNotifiers - 1] = null;
            countNotifiers--;
            System.out.println("Notifier removed.");
        }
    }

    @Override
    public void reportEvent(Event event) throws EventManagerException {
        if (events.length == MAX) {
            increaseEventArraySize();
        }

        this.events[countEvents] = event;
        this.countEvents++;
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
            for (int i = findEvent(event); i < countEvents - 1; i++) {
                events[i] = events[i + 1];
            }
            events[countEvents - 1] = null;
            countEvents--;
            System.out.println("Event removed.");
        }
    }

    @Override
    public Event[] getEvent(EventPriority ep) {
        Event[] priority = new Event[countEvents];
        int j = 0;
        for (int i = 0; i < countEvents - 1; i++) {
            if (events[i].getPriority().equals(getEvent(ep))) {
                priority[j++] = events[i];
            }
        }
        return priority;
    }

    @Override
    public Event[] getEvent(Class type) {
        Event[] classType = new Event[countEvents];
        int j = 0;
        if (events != null) {
            for (int i = 0; i < countEvents - 1; i++) {

                if (events[i] instanceof Accident) {
                    classType[j] = events[i];
                    j++;
                }
                if (events[i] instanceof Failure) {
                    classType[j] = events[i];
                    j++;
                }
            }
        }
        return classType;
    }

    @Override
    public Event[] getEvent(LocalDate ld) {
        Event[] localDate = new Event[events.length];
        int j = 0;
        for (int i = 0; i < events.length; i++) {
            if (events[i].getDate().equals(getEvent(ld))) {
                localDate[j++] = events[i];
            }
        }
        return localDate;
    }

    @Override
    public Event[] getEvent(LocalDate ld, LocalDate ld1) {
        Event[] dates = new Event[events.length];
        int j = 0;
        for (int i = 0; i < events.length; i++) {
            if (ld.compareTo(this.events[i].getDate()) >= 0 && ld1.compareTo(this.events[i].getDate()) <= 0) {
                j++;
            }
        }
        dates = new Event[j];
        j = 0;

        for (int i = 0; i < events.length; i++) {
            if (ld.compareTo(this.events[i].getDate()) >= 0 && ld1.compareTo(this.events[i].getDate()) <= 0) {
                dates[j] = events[i];
                j++;
            }
        }
        return dates;
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

            for (int i = 0; i < countNotifiers; i++) {
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

        if (event == null) {
            return -1;
        }

        for (int i = 0; i < countEvents; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }

        return -1;
    }
}