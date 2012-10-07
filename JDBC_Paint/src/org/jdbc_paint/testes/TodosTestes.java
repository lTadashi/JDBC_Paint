package org.jdbc_paint.testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TesteControleDesenho.class, TesteDesenho.class,
		TesteControlePrimitivas.class })
public class TodosTestes {

}
