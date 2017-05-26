//package com.jfruit.service;
//
//public class FormattedTableCellFactory {
//	  public FormattedTableCellFactory() {
//	    }
//
//	    @Override
//	    public TableCell<S, T> call(TableColumn<S, T> p) {
//	        TableCell<S, T> cell = new TableCell<S, T>() {
//	            @Override
//	            protected void updateItem(Object item, boolean empty) {
//	                // CSS Styles
//	                String planNotAssignedStyle = "planNotAssigned";
//	                String planAssignedStyle = "planAssigned";
//	                String vfcRecoveredStyle = "vfcRecovered";
//	                String defaultTableStyle = "defaultTableStyle";
//	                String cssStyle = "";
//
//	                InboundBean inboundBean = null;
//	                if( getTableRow() != null ) {
//	                    inboundBean = (InboundBean) getTableRow().getItem();
//	                }
//
//	                //Remove all previously assigned CSS styles from the cell.
//	                getStyleClass().remove(planAssignedStyle);
//	                getStyleClass().remove(planNotAssignedStyle);
//	                getStyleClass().remove(vfcRecoveredStyle);
//	                getStyleClass().remove(defaultTableStyle);
//
//	                super.updateItem((T) item, empty);
//
//	                //Determine how to format the cell based on the status of the container.
//	                if( inboundBean == null ) {
//	                    cssStyle = defaultTableStyle;
//	                } else if( inboundBean.isRecovered() ) {
//	                    cssStyle = vfcRecoveredStyle;
//	                } else if( inboundBean.getVfcPlan() != null && inboundBean.getVfcPlan().length() > 0 ) {
//	                    cssStyle = planAssignedStyle;
//	                } else {
//	                    cssStyle = planNotAssignedStyle;
//	                }
//
//	                //Set the CSS style on the cell and set the cell's text.
//	                getStyleClass().add(cssStyle);
//	                if( item != null ) {
//	                    setText( item.toString()  );
//	                } else {
//	                    setText( "" );
//	                }
//	            }
//	        };
//	        return cell;
//	    }
//}
