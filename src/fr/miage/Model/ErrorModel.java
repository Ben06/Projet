package fr.miage.Model;

public class ErrorModel
{
	
	/**
	 * erreur de chargement de plugin
	 * 0, aucun probl�me
	 * 1, n'impl�mente pas l'interface demand�e
	 * 2, l'interface n'est pas pr�sente avec le plugin dans le dossier de plugin
	 * 3, pas le bon nombre de m�thode
	 * 4, plugin inexistant
	 * 5, pas une classe
	 */
	private static int pluginErrorCode;
	
	/**
	 * plugin sur lequel traite l'erreur
	 */
	private static String pluginNameProblem;
	
	/**
	 * nombre d'occurence de l'erreur en question
	 */
	private static int errorOccurence;

	/**
	 * erreur d'�xecution de plugin
	 * 0, aucun probl�me
	 * 1, code �rron�
	 * 2, Acc�s ill�gal
	 * 3, Argument ill�gal
	 * 4, Erreur d'invocation
	 */
	private static int pluginInternalErrorCode;
	
	private static String internalErrorLogs;
	
	public static int getPluginErrorCode()
	{
		return pluginErrorCode;
	}

	public static void setPluginErrorCode(int pluginErrorCode)
	{
		ErrorModel.pluginErrorCode = pluginErrorCode;
	}

	public static int getErrorOccurence()
	{
		return errorOccurence;
	}

	public static void setErrorOccurence(int errorOccurence)
	{
		ErrorModel.errorOccurence = errorOccurence;
	}

	public static int getPluginInternalErrorCode()
	{
		return pluginInternalErrorCode;
	}

	public static void setPluginInternalErrorCode(int pluginInternalErrorCode)
	{
		ErrorModel.pluginInternalErrorCode = pluginInternalErrorCode;
	}

	public static String getInternalErrorLogs()
	{
		return internalErrorLogs;
	}

	public static void setInternalErrorLogs(String internalErrorLogs)
	{
		ErrorModel.internalErrorLogs = internalErrorLogs;
	}

	public static String getPluginNameProblem()
	{
		return pluginNameProblem;
	}

	public static void setPluginNameProblem(String pluginNameProblem)
	{
		ErrorModel.pluginNameProblem = pluginNameProblem;
	}
	
}
