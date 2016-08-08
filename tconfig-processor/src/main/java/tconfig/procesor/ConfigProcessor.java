package tconfig.procesor;


import javax.lang.model.element.TypeElement;

interface ConfigProcessor {

    void processConfig(TypeElement interfaceElement);
}
