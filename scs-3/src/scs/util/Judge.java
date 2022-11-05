package scs.util;

import scs.common.Error;
import scs.common.ErrorType;
import scs.common.Global;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Hmm... file comparison.
 */
public class Judge {
	public static double judge(String answer, String submission) {
		List<String> answerLines;
		List<String> submissionLines;
		int count = 0;

		try {
			answerLines = Files.readAllLines(Paths.get(answer));
			submissionLines = Files.readAllLines(Paths.get(submission));
		} catch (IOException e) {
			return Global.INVALID_SCORE;
		}

		if (answerLines.size() == 0) {
			Error.log(ErrorType.EMPTY_ANSWER);
			return Global.INVALID_SCORE;
		} else if (answerLines.size() != submissionLines.size()) {
			Error.log(ErrorType.ANSWER_LINE_MISMATCH);
			return Global.INVALID_SCORE;
		}

		int cnt = 0;
		for (int i = 0; i < answerLines.size(); i++) {
			String ans = answerLines.get(i);
			String sub = submissionLines.get(i);

			if (ans.equalsIgnoreCase(sub)) {
				cnt++;
			}
		}

		return (double) cnt / (double) answerLines.size() * 100.0;
	}
}
