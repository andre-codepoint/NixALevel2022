package com.nixalevel.command;

import com.nixalevel.request.InputDTO;
import com.nixalevel.exceptions.LSException;
import com.nixalevel.model.Colors;
import com.nixalevel.model.ColorHistory;
import com.nixalevel.model.Light;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;

public class LSHistoryCommand implements Command<Map<String, List<ColorHistory>>> {
    private final SessionFactory sessionFactory;
    private final InputDTO context;
    public LSHistoryCommand(SessionFactory sessionFactory, InputDTO context) {
        this.sessionFactory = sessionFactory;
        this.context = context;
    }
    @Override
    public Map<String, List<ColorHistory>> execute() throws LSException {

        EntityManager entityManager = null;

        try {
            entityManager = sessionFactory.createEntityManager();
            entityManager.getTransaction().begin();
            intervalCheck(entityManager);
            TypedQuery<Colors> queryColor = entityManager.createQuery("select c from Colors c", Colors.class);
            List<Colors> allColor = queryColor.getResultList();
            List<Colors> userInputColor = createUserColor(allColor);
            TypedQuery<String> lightsLabel = entityManager.createQuery("select l.label from Light l", String.class);
            List<String> labelName = lightsLabel.getResultList();
            int i;
            if (!(labelName.contains(context.label()))) {
                i = new Random().nextInt(context.colors().size());
                var randomUserColor = userInputColor.get(i);
                Light light = new Light();
                light.setLabel(context.label());
                light.setColor(randomUserColor);
                light.setEnabled(false);
                entityManager.persist(light);
            }
            TypedQuery<Light> queryLights = entityManager.createQuery("select s from Light s where s.label = ?1", Light.class);
            queryLights.setParameter(1, context.label());
            List<Light> entityLights = queryLights.getResultList();
            Light light = entityLights.get(0);
            if (light.isEnabled()) {
                throw new LSException("The light is exist but enable on");
            }

            light.setEnabled(true);
            long count = context.switchNumber();
            Colors oldColor;
            Colors newColor;
            do {
                oldColor = light.getColor();
                do {
                    i = new Random().nextInt(context.colors().size());
                    newColor = userInputColor.get(i);
                } while (oldColor.getName().equals(newColor.getName()));
                light.setColor(newColor);
                light.setEnabled(false);
                Timestamp executionTime = Timestamp.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC));
                ColorHistory colorHistory = new ColorHistory();
                colorHistory.setLight(light);
                colorHistory.setOldColor(oldColor);
                colorHistory.setNewColor(light.getColor());
                colorHistory.setChangedAt(executionTime);
                entityManager.persist(colorHistory);
                Thread.sleep(context.intervalChange() * 1000);
                count--;
            } while (count != 0);
            TypedQuery<ColorHistory> queryEntityFromColorHistoryDB = entityManager.createQuery(
                    "select ch from ColorHistory ch", ColorHistory.class);
            List<ColorHistory> entityFromColorHistoryDB = queryEntityFromColorHistoryDB.getResultList();
            Map<String, List<ColorHistory>> collectionDTO = new LinkedHashMap<>();
            collectionDTO.put(context.label(), entityFromColorHistoryDB);
            entityManager.getTransaction().commit();
            return collectionDTO;
        } catch (LSException e) {
            entityManager.getTransaction().rollback();
            throw e;
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new LSException(e);
        }
    }

    private List<Colors> createUserColor(List<Colors> allColor) {
        List<Colors> listColorUser = new ArrayList<>();
        for (String string : context.colors()) {
            for (Colors colors : allColor) {
                if (string.equals(colors.getName())) {
                    listColorUser.add(colors);
                }
            }
        }
        return listColorUser;
    }

    private void intervalCheck(EntityManager entityManager) throws LSException {
        TypedQuery<String> queryColors = entityManager.createQuery("select c.name from Colors c", String.class);
        List<String> colors = queryColors.getResultList();
        if (!(new HashSet<>(colors).containsAll(context.colors()))) {
            throw new LSException("Wrong colors list");
        }
        if (context.intervalChange() < 1) {
            throw new LSException("The interval is less than 1");
        }
        if (context.switchNumber() < 1) {
            throw new LSException("The number of switches is less than 1");
        }
    }

}
