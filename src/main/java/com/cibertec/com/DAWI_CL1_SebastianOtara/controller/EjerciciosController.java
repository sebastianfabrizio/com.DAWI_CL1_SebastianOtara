package com.cibertec.com.DAWI_CL1_SebastianOtara.controller;
import com.cibertec.com.DAWI_CL1_SebastianOtara.model.computadora;
import com.cibertec.com.DAWI_CL1_SebastianOtara.model.factorial;
import com.cibertec.com.DAWI_CL1_SebastianOtara.model.mes;
import com.cibertec.com.DAWI_CL1_SebastianOtara.model.monto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class EjerciciosController {

    /*EJERCICIO 1*/
    @GetMapping("/venta")
    public String calcular(Model model) {
        model.addAttribute("computadoramodel",
                new computadora());
        return "ventacomputadoras";
    }

    @PostMapping("/cantidadcomputadoras")
    public String calcularMonto(computadora computadoraModel, Model model) {
        int cantidad = computadoraModel.getCantidad();
        double precio = 11000;
        double totalPagar;

        if (cantidad < 5) {
            totalPagar = cantidad * precio * 0.9;
        } else if (cantidad < 10) {
            totalPagar = cantidad * precio * 0.8;
        } else {
            totalPagar = cantidad * precio * 0.6;
        }
        model.addAttribute("computadoramodel",
                new computadora());
        model.addAttribute("totalPagar", totalPagar);
        return "ventacomputadoras";
    }

/*EJERCICIO 2*/
    @GetMapping("/mes")
    public String mes(Model model) {
        model.addAttribute("mesmodel", new mes());
        return "mes";
    }

    @PostMapping("/mesprocesado")
    public String procesarMes(mes mes, Model model) {
        int numeroMes = mes.getNumeromes();
        String[] nombresMeses = {"Null", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String nombreMes = "";
        if (numeroMes >= 1 && numeroMes <= 12) {
            nombreMes = nombresMeses[numeroMes];
        } else {
            nombreMes = "Número de mes inválido";
        }
        model.addAttribute("mes", nombreMes);
        model.addAttribute("mesmodel", mes);
        return "mes";
    }

    /*Ejercicio 3*/

    @GetMapping("/monto")
    public String calculando(Model model) {
        model.addAttribute("montomodel", new monto());
        return "monto";
    }

    @PostMapping("/montoventas")
    public String calcularBonificacion(monto monto, Model model) {
        double bonificacion = 0.0;

        if (monto.getMontoventa() >= 0 && monto.getMontoventa() <= 1000) {
            bonificacion = monto.getMontoventa() * 0.10;
        } else if (monto.getMontoventa() > 1000 && monto.getMontoventa() <= 2000) {
            bonificacion = monto.getMontoventa() * 0.20;
        } else if (monto.getMontoventa() > 2000 && monto.getMontoventa() <= 3000) {
            bonificacion = monto.getMontoventa() * 0.30;
        }
        model.addAttribute("montomodel", monto);
        monto.setBonificacion(bonificacion);
        monto.setMontototal(monto.getMontoventa() + bonificacion);

        return "monto";
    }


    /*Ejercicio 4*/
    @GetMapping("/factorial")
    public String mostrarFormulario(Model model) {
        model.addAttribute("factorial", new factorial());
        return "factorial";
    }

    @PostMapping("/calcularfactorial")
    public String calcularFactorial(Model model, factorial numero) {
        long factorial = 1;
        for (int i = 1; i <= numero.getValor(); i++) {
            factorial *= i;
        }
        model.addAttribute("factorial", numero);
        numero.setFactorial(factorial);
        return "factorial";
    }

}