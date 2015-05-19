package primefaces;

/*
 * Copyright 2009-2012 Prime Teknoloji.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.component.behavior.Behavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.FacesListener;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.component.datatable.DataTable;

public class CellEditEvent extends AjaxBehaviorEvent {

    private Object oldValue;

    private Object newValue;

    private int rowIndex;

    private UIColumn column;

    public CellEditEvent(UIComponent component, Behavior behavior, int rowIndex, UIColumn column) {
        super(component, behavior);
        this.rowIndex = rowIndex;
        this.column = column;
        this.oldValue = resolveValue();
    }

    @Override
    public boolean isAppropriateListener(FacesListener faceslistener) {
        return (faceslistener instanceof AjaxBehaviorListener);
    }

    @Override
    public void processListener(FacesListener faceslistener) {
        ((AjaxBehaviorListener) faceslistener).processAjaxBehavior(this);
    }

    public Object getOldValue() {
        return this.oldValue;
    }

    public Object getNewValue() {
        if (newValue == null) {
            newValue = resolveValue();
        }
        return newValue;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public UIColumn getColumn() {
        return column;
    }

    public void setColumn(UIColumn column) {
        this.column = column;
    }

    private Object resolveValue() {
        DataTable data = (DataTable) source;
        data.setRowModel(rowIndex);

        for (UIComponent child : column.getChildren()) {
            if (child instanceof CellEditor) {
                ValueHolder input = (ValueHolder) child.getFacet("input");
                return input.getValue();
            }
        }

        return null;
    }
}